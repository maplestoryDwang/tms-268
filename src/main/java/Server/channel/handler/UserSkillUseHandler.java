package Server.channel.handler;

import Client.MapleCharacter;
import Client.MapleClient;
import Client.SecondaryStat;
import Client.SecondaryStatValueHolder;
import Client.force.MapleForceFactory;
import Client.skills.Skill;
import Client.skills.SkillFactory;
import Client.skills.handler.AbstractSkillHandler;
import Client.skills.handler.SkillClassApplier;
import Config.constants.SkillConstants;
import Config.constants.enums.UserChatMessageType;
import Config.constants.skills.爆拳槍神;
import Config.constants.skills.狂豹獵人;
import Net.server.buffs.MapleStatEffect;
import Opcode.Headler.OutHeader;
import Packet.ForcePacket;
import Packet.MaplePacketCreator;
import tools.data.MaplePacketReader;

import java.awt.*;



public class UserSkillUseHandler {

    public static void userSkillUseRequest(MaplePacketReader inPacket, MapleClient c, MapleCharacter chr) {
        if (chr != null && !chr.hasBlockedInventory() && chr.getMap() != null && inPacket.available() >= 9L) {
            chr.updateTick(inPacket.readInt());
            int skillId = inPacket.readInt();
            if (skillId != 0) {
                inPacket.readInt();
                if (SkillConstants.isZeroSkill(skillId)) {
                    inPacket.readByte();
                }

                int skilllv = inPacket.readInt();
                Skill skill = SkillFactory.getSkill(skillId);
                if (skill == null) {
                    c.sendEnableActions();
                } else {
                    if (c.getPlayer().isGm() && skill.getId() > 0) {
                        MapleCharacter var10000 = c.getPlayer();
                        String var10002 = SkillFactory.getSkillName(skill.getId());
                        var10000.dropMessage(40, "[Skill] " + var10002 + " / skillID :" + skill.getId() + " / skillLV :" + skilllv);
                    }

                    if (SkillFactory.isBlockedSkill(skillId)) {
                        chr.dropMessage(5, "由於<" + SkillFactory.getSkillName(skillId) + ">技能數據異常,暫未開放使用.");
                        c.sendEnableActions();
                    } else {
                        if (SkillConstants.isZeroSkill(skillId)) {
                            inPacket.readByte();
                        }

                        if (chr.isDebug()) {
                            chr.dropMessage(1, "[Skill Use] SkillID:" + skillId + " skill is null!");
                        }

                        int skillLevel = skilllv;
                        int linkedAttackSkill = SkillConstants.getLinkedAttackSkill(skillId);
                        int checkSkillLevel = chr.getTotalSkillLevel(linkedAttackSkill);
                        MapleStatEffect effect = chr.inPVP() ? skill.getPVPEffect(checkSkillLevel) : skill.getEffect(checkSkillLevel);
                        SecondaryStatValueHolder holder;
                        if (effect == null) {
                            holder = chr.getBuffStatValueHolder(linkedAttackSkill);
                            if (holder != null && holder.effect != null) {
                                effect = holder.effect;
                                skillLevel = holder.effect.getLevel();
                            }
                        }

                        AttackInfo ai = new AttackInfo();
                        ai.skillposition = null;
                        inPacket.readInt();
                        inPacket.readInt();
                        inPacket.readInt();
                        DamageParse.calcAttackPosition(inPacket, chr, ai);
                        Point pos = ai.skillposition;
                        boolean passive = false;
                        inPacket.readByte();
                        inPacket.readByte();
                        inPacket.readByte();
                        inPacket.readShort();
                        int plus = inPacket.readInt();
                        inPacket.readByte();
                        inPacket.readByte();
                        if (plus == 0) {
                            inPacket.readInt();
                        }

                        if (chr.isDebug()) {
                            chr.dropSpouseMessage(UserChatMessageType.粉, "[Skill Use] Effect:" + SkillFactory.getSkillName(skillId) + "(" + skillId + ") Level: " + skillLevel);
                            if (effect == null) {
                                chr.dropMessage(1, "[Skill Use] SkillID:" + skillId + " Lv:" + skillLevel + " Effect is null!");
                            }

                            if (linkedAttackSkill != skillId) {
                                chr.dropSpouseMessage(UserChatMessageType.粉, "[Skill Use] Linked SkillID:" + SkillFactory.getSkillName(linkedAttackSkill) + "(" + linkedAttackSkill + ") Level: " + checkSkillLevel);
                            }
                        }

                        if (!chr.isGm() && chr.isSkillCooling(linkedAttackSkill)) {
                            chr.dropMessage(5, "還無法使用技能。");
                        } else {
                            if (skillId == 400031000) {
                                MapleForceFactory forceFactory = MapleForceFactory.getInstance();
                                chr.getMap().broadcastMessage(ForcePacket.forceAtomCreate(forceFactory.getMapleForce(chr, effect, 0, 0)));
                            }

                            if (skillId == 40011288) {
                                chr.getClient().outPacket(OutHeader.LP_TemporaryStatSet.getValue(), new Object[]{"00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 01 00 18 86 62 02 FE FF FF FF 00 00 E8 79 9D FD 00 00 00 00 00 00 00 00 00 00 00 01 01 01 00 00 00 00 00 17"});
                                chr.applyHayatoStance((int)(-0.15 * (double)chr.getSpecialStat().getHayatoPoint()));
                            }

                            if (skillId != 37101001 && skillId != 37111003 && skillId != 151121003) {
                                c.sendEnableActions();
                            }

                            AbstractSkillHandler handler = null;
                            if (effect != null) {
                                handler = effect.getSkillHandler();
                            }

                            int handleRes = -1;
                            if (handler != null) {
                                SkillClassApplier applier = new SkillClassApplier();
                                applier.effect = effect;
                                applier.passive = passive;
                                applier.pos = pos;
                                applier.ai = ai;
                                applier.plus = plus;
                                handleRes = handler.onSkillUse(inPacket, c, chr, applier);
                                if (handleRes == 0) {
                                    return;
                                }

                                if (handleRes == 1) {
                                    effect = applier.effect;
                                    passive = applier.passive;
                                    pos = applier.pos;
                                    ai = applier.ai;
                                }
                            }

                            if ((inPacket.available() == 4L || inPacket.available() == 5L) && skillId != 30001061) {
                                pos = inPacket.readPos();
                            } else if (inPacket.available() == 12L || inPacket.available() == 13L) {
                                inPacket.readInt();
                                pos = inPacket.readPosInt();
                            }

                            SecondaryStatValueHolder secondaryStatValueHolder;
                            if (handleRes != -1 || skillId != 80012015 || (secondaryStatValueHolder = chr.getBuffStatValueHolder(SecondaryStat.ErdaStack)) != null && secondaryStatValueHolder.value >= 6) {
                                Skill linkedSkill = SkillFactory.getSkill(linkedAttackSkill);
                                if (skill.isChargeSkill() || linkedSkill.isChargeSkill()) {
                                    chr.setKeyDownSkill_Time(0L);
                                    MapleStatEffect eff;
                                    if (skill.isChargeSkill()) {
                                        eff = chr.getSkillEffect(skillId);
                                        if (eff != null && eff.getCooldown() > 0) {
                                            if (SkillConstants.isKeydownSkillCancelGiveCD(skillId) && !chr.isSkillCooling(skillId)) {
                                                chr.registerSkillCooldown(chr.getSkillEffect(skillId), true);
                                            }

                                            if (!chr.isSkillCooling(skillId)) {
                                                chr.send(MaplePacketCreator.skillCooltimeSet(skillId, 0));
                                            }
                                        }
                                    }

                                    if (linkedSkill.isChargeSkill()) {
                                        eff = chr.getSkillEffect(linkedAttackSkill);
                                        if (eff != null && eff.getCooldown() > 0) {
                                            if (SkillConstants.isKeydownSkillCancelGiveCD(linkedAttackSkill) && !chr.isSkillCooling(linkedAttackSkill)) {
                                                chr.registerSkillCooldown(chr.getSkillEffect(linkedAttackSkill), true);
                                            }

                                            if (!chr.isSkillCooling(linkedAttackSkill)) {
                                                chr.send(MaplePacketCreator.skillCooltimeSet(linkedAttackSkill, 0));
                                            }
                                        }
                                    }
                                }

                                if (SkillConstants.isOnOffSkill(skillId) && chr.getBuffStatValueHolder(skillId) != null) {
                                    chr.cancelEffect(effect, false, -1L);
                                } else {
                                    if (effect != null) {
                                        effect.applyTo(chr, pos, passive);
                                    }

                                    if (passive) {
                                    }

                                }
                            } else {
                                chr.dropMessage(5, "堆疊不足。");
                            }
                        }
                    }
                }
            }
        }
    }
}
