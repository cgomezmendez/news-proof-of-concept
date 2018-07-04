package me.cristiangomez.news.feed;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.util.parse.StoryJsonParser;

public class FeedPresenter implements FeedContract.Presenter {
    private final FeedContract.View feedView;

    public FeedPresenter(@NonNull  FeedContract.View feedView) {
        this.feedView = feedView;
        feedView.setPresenter(this);
    }

    @Override
    public void loadStories() {
        // TODO: implement logic
        List<Story> stories = new ArrayList<>();
        String dummyStories = "[\n" +
                "  {\n" +
                "    \"id\": \"sport/live/2018/jul/04/wimbledon-2018-federer-serena-williams-venus-swan-wozniacki-cilic-day-three-live\",\n" +
                "    \"type\": \"liveblog\",\n" +
                "    \"sectionId\": \"sport\",\n" +
                "    \"sectionName\": \"Sport\",\n" +
                "    \"webPublicationDate\": \"2018-07-04T18:19:47Z\",\n" +
                "    \"webTitle\": \"Wimbledon 2018: Wozniacki goes out, Wawrinka in trouble and Federer wins and more â€“ live!\",\n" +
                "    \"webUrl\": \"https://www.theguardian.com/sport/live/2018/jul/04/wimbledon-2018-federer-serena-williams-venus-swan-wozniacki-cilic-day-three-live\",\n" +
                "    \"apiUrl\": \"https://content.guardianapis.com/sport/live/2018/jul/04/wimbledon-2018-federer-serena-williams-venus-swan-wozniacki-cilic-day-three-live\",\n" +
                "    \"fields\": {\n" +
                "      \"trailText\": \"Get all the latest from day three in SW19 with Roger Federer and the Williams sisters in action. Join Katy Murrells\",\n" +
                "      \"byline\": \"Katy Murrells\",\n" +
                "      \"lastModified\": \"2018-07-04T18:19:47Z\",\n" +
                "      \"thumbnail\": \"https://media.guim.co.uk/aca646ff9bd7d521bd013f3e8119247c113847e9/671_222_4338_2603/500.jpg\"\n" +
                "    },\n" +
                "    \"isHosted\": false,\n" +
                "    \"pillarId\": \"pillar/sport\",\n" +
                "    \"pillarName\": \"Sport\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"business/nils-pratley-on-finance/2018/jul/04/food-fight-over-premier-its-hard-to-pick-a-winner\",\n" +
                "    \"type\": \"article\",\n" +
                "    \"sectionId\": \"business\",\n" +
                "    \"sectionName\": \"Business\",\n" +
                "    \"webPublicationDate\": \"2018-07-04T18:16:16Z\",\n" +
                "    \"webTitle\": \"Food fight over Premier â€“ it's hard to pick a winner\",\n" +
                "    \"webUrl\": \"https://www.theguardian.com/business/nils-pratley-on-finance/2018/jul/04/food-fight-over-premier-its-hard-to-pick-a-winner\",\n" +
                "    \"apiUrl\": \"https://content.guardianapis.com/business/nils-pratley-on-finance/2018/jul/04/food-fight-over-premier-its-hard-to-pick-a-winner\",\n" +
                "    \"fields\": {\n" +
                "      \"trailText\": \"Activists want action on an overpaid chief exec, while loyalists plead for more time\",\n" +
                "      \"byline\": \"Nils Pratley\",\n" +
                "      \"lastModified\": \"2018-07-04T18:17:44Z\",\n" +
                "      \"thumbnail\": \"https://media.guim.co.uk/7bdbd2a2f4a25f2a6887b739e4ae4b0a7d3d2ec3/0_244_6570_3942/500.jpg\"\n" +
                "    },\n" +
                "    \"isHosted\": false,\n" +
                "    \"pillarId\": \"pillar/news\",\n" +
                "    \"pillarName\": \"News\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"world/ng-interactive/2018/jul/03/thailand-cave-rescue-where-were-the-boys-found-and-how-can-they-be-rescued\",\n" +
                "    \"type\": \"interactive\",\n" +
                "    \"sectionId\": \"world\",\n" +
                "    \"sectionName\": \"World news\",\n" +
                "    \"webPublicationDate\": \"2018-07-04T18:16:16Z\",\n" +
                "    \"webTitle\": \"Thailand cave rescue: where were the boys found and how can they be rescued?\",\n" +
                "    \"webUrl\": \"https://www.theguardian.com/world/ng-interactive/2018/jul/03/thailand-cave-rescue-where-were-the-boys-found-and-how-can-they-be-rescued\",\n" +
                "    \"apiUrl\": \"https://content.guardianapis.com/world/ng-interactive/2018/jul/03/thailand-cave-rescue-where-were-the-boys-found-and-how-can-they-be-rescued\",\n" +
                "    \"fields\": {\n" +
                "      \"trailText\": \"When 12 missing Thai boys and their football coach were found alive deep in a cave system on Monday, joy was tempered with anxiety. The caves are flooded with surging monsoon waters, pitch black and in places too narrow to allow rescuers to pass while carrying scuba gear. None of the boys can swim or dive. The dilemma: risk a highly dangerous escape or wait possibly months for the waters to subside\",\n" +
                "      \"byline\": \"SeÃ¡n Clarke, Paul Torpey, Paul Scruton, Michael Safi, Daniel Levitt, Pablo GutiÃ©rrez and Chris Watson\",\n" +
                "      \"lastModified\": \"2018-07-04T18:16:46Z\",\n" +
                "      \"thumbnail\": \"https://media.guim.co.uk/4635d72620f40f31fb965ed3d41ed9d6d83ff666/512_320_2199_1320/500.jpg\"\n" +
                "    },\n" +
                "    \"isHosted\": false,\n" +
                "    \"pillarId\": \"pillar/news\",\n" +
                "    \"pillarName\": \"News\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"commentisfree/2018/jul/05/i-stopped-going-to-the-gym-because-of-trump-now-i-cant-open-jars\",\n" +
                "    \"type\": \"article\",\n" +
                "    \"sectionId\": \"commentisfree\",\n" +
                "    \"sectionName\": \"Opinion\",\n" +
                "    \"webPublicationDate\": \"2018-07-04T18:00:10Z\",\n" +
                "    \"webTitle\": \"I stopped going to the gym because of Trump. Now I can't open jars | Brigid Delaney\",\n" +
                "    \"webUrl\": \"https://www.theguardian.com/commentisfree/2018/jul/05/i-stopped-going-to-the-gym-because-of-trump-now-i-cant-open-jars\",\n" +
                "    \"apiUrl\": \"https://content.guardianapis.com/commentisfree/2018/jul/05/i-stopped-going-to-the-gym-because-of-trump-now-i-cant-open-jars\",\n" +
                "    \"fields\": {\n" +
                "      \"trailText\": \"I tried out Donald Trumpâ€™s theory on exercise. I walked. I this, I that. I was weak\",\n" +
                "      \"byline\": \"Brigid Delaney\",\n" +
                "      \"lastModified\": \"2018-07-04T18:02:43Z\",\n" +
                "      \"thumbnail\": \"https://media.guim.co.uk/335e6c1ee09926ca933ee6730d69dd04c62ac0e2/0_343_5184_3110/500.jpg\"\n" +
                "    },\n" +
                "    \"isHosted\": false,\n" +
                "    \"pillarId\": \"pillar/opinion\",\n" +
                "    \"pillarName\": \"Opinion\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": \"australia-news/2018/jul/05/coalition-reveals-details-of-radical-attempt-to-fix-gst-formula\",\n" +
                "    \"type\": \"article\",\n" +
                "    \"sectionId\": \"australia-news\",\n" +
                "    \"sectionName\": \"Australia news\",\n" +
                "    \"webPublicationDate\": \"2018-07-04T18:00:09Z\",\n" +
                "    \"webTitle\": \"Coalition reveals details of radical attempt to fix GST formula\",\n" +
                "    \"webUrl\": \"https://www.theguardian.com/australia-news/2018/jul/05/coalition-reveals-details-of-radical-attempt-to-fix-gst-formula\",\n" +
                "    \"apiUrl\": \"https://content.guardianapis.com/australia-news/2018/jul/05/coalition-reveals-details-of-radical-attempt-to-fix-gst-formula\",\n" +
                "    \"fields\": {\n" +
                "      \"trailText\": \"Turnbull unveils complicated formula to ensure every stateâ€™s capacity is at least equal to NSW or Victoria\",\n" +
                "      \"byline\": \"Gareth Hutchens\",\n" +
                "      \"lastModified\": \"2018-07-04T18:02:41Z\",\n" +
                "      \"thumbnail\": \"https://media.guim.co.uk/5dc0e1a5a88c1bd5fc0a3f07ab2de4cd9bc9dbdb/323_457_5225_3135/500.jpg\"\n" +
                "    },\n" +
                "    \"isHosted\": false,\n" +
                "    \"pillarId\": \"pillar/news\",\n" +
                "    \"pillarName\": \"News\"\n" +
                "  }]";
        StoryJsonParser storyJsonParser = new StoryJsonParser();
        try {
            JSONArray jsonArray = new JSONArray(dummyStories);
            for (int i = 0; i < jsonArray.length(); i++) {
                stories.add(storyJsonParser.parseJson(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.feedView.showStories(stories);
    }

    @Override
    public void openStoryDetails(@NonNull Story requestedStory) {
        // TODO: implement logic
    }

    @Override
    public void start() {
        loadStories();
    }
}
