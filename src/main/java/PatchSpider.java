import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@Slf4j
class PatchSpider {
    private static PatchSpider ourInstance = new PatchSpider();

    static PatchSpider getInstance() {
        return ourInstance;
    }

    private static final String BASE_PAGE = "https://playoverwatch.com/en-us/game/patch-notes/pc/";
    private static final String NAV_XPATH = "//div[@class='PatchNotesSideNav']";
    private static final int DIV_UL_CHILD_NUM = 1;

    private WebClient webClient;

    private PatchSpider() {
        log.debug("Initializing web spider...");
        webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        log.debug("PatchSpider initialized.");
    }

    @SneakyThrows
    PatchInfo getLatestPatch() {
        log.debug("Connecting to " + BASE_PAGE);

        val page = (HtmlPage) webClient.getPage(BASE_PAGE);
        val patchNotesNav = (HtmlDivision) page.getByXPath(NAV_XPATH).get(0);
        val patchNotesList = (HtmlUnorderedList) patchNotesNav.getChildNodes().get(DIV_UL_CHILD_NUM);
        val recentPatchContainer = (HtmlListItem) patchNotesList.getFirstChild();
        val recentPatchAnchor = (HtmlAnchor) recentPatchContainer.getFirstChild();
        val recentPatchHeader = (HtmlHeading3) recentPatchAnchor.getFirstChild();

        val recentPatchName = recentPatchHeader.getTextContent();
        val recentPatchLink = BASE_PAGE + recentPatchAnchor.getHrefAttribute();

        return new PatchInfo(recentPatchName, recentPatchLink);
    }
}
