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

import com.l2jserver.gameserver.handler.CommunityBoardHandler;
import com.l2jserver.gameserver.handler.IWriteBoardHandler;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * Buffs board.
 * @author evgeny mokhnachev
 */
public class BuffsBoard implements IWriteBoardHandler
{
	private static final String[] COMMANDS =
	{
		"_bbsbuffs"
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
			CommunityBoardHandler.getInstance().addBypass(activeChar, "Buffs", command);
			CommunityBoardHandler.separateAndSend("<html><body><br><br><center>У вас есть адена: " + activeChar.getAdena() + "</center><br><br></body></html>", activeChar);
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
