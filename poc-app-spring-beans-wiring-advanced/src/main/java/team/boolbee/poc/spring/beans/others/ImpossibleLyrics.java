package team.boolbee.poc.spring.beans.others;

public class ImpossibleLyrics implements Poem {
	private static String[] LINES = {
			"Hmm",
			"I remember years ago",
			"Someone told me I should take",
			"Caution when it comes to love, I did",
			"And you were strong and I was not",
			"My illusion, my mistake",
			"I was careless, I forgot, I did",
			"And now",
			"When all is done, there is nothing to say",
			"You have gone and so effortlessly",
			"You have won, you can go ahead tell them",
			"Tell them all I know now",
			"Shout it from the roof tops",
			"Write it on the sky line",
			"All we had is gone now",
			"Tell them I was happy",
			"And my heart is broken",
			"All my scars are open",
			"Tell them what I hoped would be impossible,",
			"Impossible",
			"Impossible",
			"Impossible",
			"Falling out of love is hard",
			"Falling for betrayal is worse",
			"Broken trust and broken hearts",
			"I know, I know",
			"And thinking all you need is there",
			"Building faith on love and words",
			"Empty promises will wear",
			"I know",
			"I know and now",
			"When all�"
	};

	public ImpossibleLyrics() {
	}

	public void recite() {
		for (int i = 0; i < LINES.length; i++) {
			System.out.println(LINES[i]);
		}
	}
}
