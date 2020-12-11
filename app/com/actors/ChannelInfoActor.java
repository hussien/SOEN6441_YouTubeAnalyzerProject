package com.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import com.google.inject.Singleton;
import service.YouTubeService;
import util.YoutubeAPI;

@Singleton
public class ChannelInfoActor extends AbstractActor {
    public static final class ChannelInfo {
        String channelID;
        YouTubeService youTubeService;

        public ChannelInfo(String channelID, YouTubeService youTubeService){
            this.channelID = channelID;
            this.youTubeService = youTubeService;
        }

        public String getChannelID() {
            return channelID;
        }

        public YouTubeService getYoutubeAPI() {
            return youTubeService;
        }
    }




//    public static Behavior<ChannelInfo> create() {
//        return Behaviors.setup((ctx) -> new ChannelInfoActor(ctx));
//    }
//
//    public ChannelInfoActor(ActorContext context)
//    {
//        super();
//    }

    public static Props getProps()
    {
        return Props.create(ChannelInfoActor.class);
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ChannelInfo.class,
                        channelInfo -> {
                            ChannelResponse channelResponse = channelInfo.youTubeService.getChannelResult(channelInfo.getChannelID());
                            sender().tell(channelResponse, self());
                        })
                .build();
    }
}
