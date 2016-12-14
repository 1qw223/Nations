package com.arckenver.nations.listener;

import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;

import com.arckenver.nations.DataHandler;
import com.arckenver.nations.object.Nation;
import com.arckenver.nations.service.NationMessageChannel;

@Plugin(id = "spongenationtag", name = "Sponge Nation Chat Tag", version = "1.1",
description = "Towny like chat formating", authors = {"Carrot"})
public class ChatListener
{

	@Listener(order = Order.LATE)
	public void onPlayerChat(MessageChannelEvent.Chat e, @First Player p)
	{
		Optional<MessageChannel> channel = e.getChannel();
		if (!channel.isPresent())
		{
			return;
		}
		Nation nation = DataHandler.getNationOfPlayer(p.getUniqueId());
		if (nation == null)
		{
			return;
		}
		MessageChannel chan = channel.get();
		if (chan.equals(MessageChannel.TO_ALL))
		{
			e.setMessage(Text.of(TextColors.WHITE, " [", TextColors.DARK_AQUA, nation.getName(), TextColors.WHITE,  "] "), e.getMessage());
		}
		else if (chan instanceof NationMessageChannel)
		{
			e.setMessage(Text.of(TextColors.WHITE, " {", TextColors.YELLOW, nation.getName(), TextColors.WHITE,  "} "), Text.of(TextColors.YELLOW, e.getMessage()));
		}
	}
}