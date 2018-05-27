import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

import javax.security.auth.login.LoginException;

@Slf4j
class Discord {
    private static Discord ourInstance;

    static void initialize() {
        ourInstance = new Discord();
    }

    static Discord getInstance() {
        if (ourInstance == null || ourInstance.jda.getStatus() == JDA.Status.DISCONNECTED) {
            initialize();
        }

        return ourInstance;
    }

    private static final String LOGIN_ERROR_MESSAGE =
            "Unable to log in with your api token. Please double check your OMNIC_KEY environmental variable.";
    private static final String PATCH_NOTES_MESSAGE =
            "New Overwatch patch notes for %s:\n%s";

    private JDA jda;

    private Discord() {
        log.debug("Starting connection to Discord...");

        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(System.getenv("OMNIC_KEY"))
                    .setGame(Game.playing("Overwatch"))
                    .buildBlocking();
        } catch (LoginException e) {
            System.err.println(LOGIN_ERROR_MESSAGE);
            throw new IllegalStateException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.debug("Connection with Discord established.");
    }

    void broadcastPatch(PatchInfo patchInfo) {
        val message = String.format(PATCH_NOTES_MESSAGE, patchInfo.getPatchName(), patchInfo.getPatchLink());
        log.debug("Broadcasting patch notes message...");

        for (Guild guild : jda.getGuilds()) {
            val overwatchChannels = guild.getTextChannelsByName("overwatch", true);

            for (TextChannel channel : overwatchChannels) {
                log.debug("Sending patch notes message to " + guild.getName() + ": #" + channel.getName());
                channel.sendMessage(message).queue();
            }
        }
    }
}
