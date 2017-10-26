/*
 * Copyright (C) 2004-2016 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.communityboard;

import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.datatables.SkillData;
import com.l2jserver.gameserver.handler.CommunityBoardHandler;
import com.l2jserver.gameserver.handler.IParseBoardHandler;
import com.l2jserver.gameserver.handler.IWriteBoardHandler;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.skills.Skill;

/**
 * Buffs board.
 * @author evgeny mokhnachev
 */
public class BuffsBoard implements IWriteBoardHandler, IParseBoardHandler
{
	private static final String[] COMMANDS =
	{
		"_bbsbuffs",
			"_selfbuff"
	};
	
	@Override
	public String[] getCommunityBoardCommands()
	{
		return COMMANDS;
	}
	
	@Override
	public boolean parseCommunityBoardCommand(String command, L2PcInstance activeChar)
	{
		if (command.equals("_bbsbuffs"))
		{
			String html = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/buffs.html");
			CommunityBoardHandler.separateAndSend(html, activeChar);
		} else if (command.equals("_selfbuff")) {
			int[] ids = new int[]{
				1040, 1068, 1035, 1043, 1044, 1073, 1077, 1078, 1085, 1204, 1032, 1036, 1045, 1048, 1086, 1240, 1242, 1243, 1388, 1389, 1062, 1087, 1059, 1268, 1303, 1460, 1257, 1259, 1304, 1397, 1353,
				1354, 1355, 1357, 1356, 1191, 1033, 1182, 1189, 1392, 1393, 1352, 271, 272, 273, 274, 275, 276, 277, 307, 309, 310, 311, 365, 366, 530, 264, 265, 266, 267, 268, 269, 270, 304, 305, 306, 308, 349, 363, 364, 529, 1007, 1009, 1002, 1006, 1251, 1252, 1253, 1284, 1308, 1309, 1310, 1362, 1363, 1390, 1391, 1413, 1461, 1003, 1004, 1005, 1008, 1249, 1250, 1260, 1261, 1282, 1364, 1365, 1414, 1415, 1416, 4699, 4700, 4702, 4703, 825, 826, 824, 828, 829, 830
			};

			for(int i : ids){
				try {
					int maxLevel = SkillData.getInstance().getMaxLevel(i);
					Skill skill = SkillData.getInstance().getSkill(i, maxLevel);
					skill.applyEffects(activeChar, activeChar, true, 14400);
				} catch (Exception e) {
					LOG.info(i + "");
					LOG.info(e.getMessage());
				}
			}
		} else {
			CommunityBoardHandler.separateAndSend("<html><body><br><br><center>Command " + command + " need development.</center><br><br></body></html>", activeChar);
		}
		return true;
	}
	
	@Override
	public boolean writeCommunityBoardCommand(L2PcInstance activeChar, String arg1, String arg2, String arg3, String arg4, String arg5)
	{
		return true;
	}
}
