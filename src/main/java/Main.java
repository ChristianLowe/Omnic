import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    private static PatchInfo latestPatch;

    public static void main(String[] args) throws Exception {
        Discord.initialize();

        val spider = PatchSpider.getInstance();
        latestPatch = spider.getLatestPatch();

        log.debug("Starting update refresh loop.");
        val scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> updateLatestPatch(spider.getLatestPatch()),
                20, 20, TimeUnit.MINUTES);
    }

    private static void updateLatestPatch(PatchInfo newPatch) {
        if (!newPatch.equals(latestPatch)) {
            log.info("Pushing new patch: " + newPatch.getPatchName());

            latestPatch = newPatch;
            Discord.getInstance().broadcastPatch(latestPatch);
        } else {
            log.debug("No new patch.");
        }
    }
}
