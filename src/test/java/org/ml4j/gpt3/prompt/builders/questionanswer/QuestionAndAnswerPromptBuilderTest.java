package org.ml4j.gpt3.prompt.builders.questionanswer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class QuestionAndAnswerPromptBuilderTest {
	
	private final String DEFAULT_Q_AND_A_PROMPT_URL = 
			"https://raw.githubusercontent.com/ml4j/gtp-3-prompt-templates/master/question-answer/"
			+ "default/examples/example1/question_answer_example_prompt_1.json";
	
	private final String TWITTER_FICTION_PROMPT_URL = 
			"https://raw.githubusercontent.com/ml4j/gpt-scrolls/master/tweets/twitter-fiction-prompt.json";
	
	private final String DEFAULT_Q_AND_A_TEMPLATE_URL = "https://raw.githubusercontent.com/ml4j/gtp-3-prompt-templates/master/question-answer/"
			+ "default/templates/question_answer_template_1.json";
	
	private final String DEFAULT_Q_AND_A_TEMPLATE_URL_2 = "https://raw.githubusercontent.com/ml4j/gtp-3-prompt-templates/master/question-answer/"
			+ "default/templates/question_answer_template_2.json";
	
	private final String CODE_EXPLANATION_Q_AND_A_PROMPT_URL = 
			"https://raw.githubusercontent.com/ml4j/gtp-3-prompt-templates/master/question-answer/code-explanation/examples/java-example-1/"
			+ "code_explanation_example_prompt_1.json";
	
	private final String CODE_EXPLANATION_Q_AND_A_TEMPLATE_URL = "https://raw.githubusercontent.com/ml4j/gtp-3-prompt-templates/master/question-answer"
			+ "/code-explanation/templates/code_explanation_template_1.json";
			
	@Test
	public void testDefaultQuestionAnswerStructureWithDefaultQuestion() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		QuestionAndAnswerPromptBuilder builder
			= new QuestionAndAnswerPromptBuilder(new URL(DEFAULT_Q_AND_A_PROMPT_URL), 
					new URL(DEFAULT_Q_AND_A_TEMPLATE_URL));
		
		String expectedPromptText = "Q: What is human life expectancy in the United States?\n" + 
				"\n" + 
				"A: Human life expectancy in the United States is 78 years.\n" + 
				"\n" + 
				"Q: Who was president of the United States in 1955?\n" + 
				"\n" + 
				"A: Dwight D. Eisenhower was president of the United States in 1955.\n" + 
				"\n" + 
				"Q: What party did he belong to?\n" + 
				"\n" + 
				"A: He belonged to the Republican Party.\n" + 
				"\n" + 
				"Q: Who was president of the United States before George W. Bush?\n" + 
				"\n" + 
				"A: Bill Clinton was president of the United States before George W. Bush.\n" + 
				"\n" + 
				"Q: Who won the World Series in 1995?\n" + 
				"\n" + 
				"A: ";
		
		String promptText = builder.buildPromptText(null);
				
		Assertions.assertEquals(expectedPromptText, promptText);
		
	}
	
	@Test
	public void testDefaultQuestionAnswerStructureWithCustomQuestion() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		QuestionAndAnswerPromptBuilder builder
		= new QuestionAndAnswerPromptBuilder(new URL(DEFAULT_Q_AND_A_PROMPT_URL), 
				new URL(DEFAULT_Q_AND_A_TEMPLATE_URL));
		
		String customQuestion = "Is this a good question?";
		
		String expectedPromptText = "Q: What is human life expectancy in the United States?\n" + 
				"\n" + 
				"A: Human life expectancy in the United States is 78 years.\n" + 
				"\n" + 
				"Q: Who was president of the United States in 1955?\n" + 
				"\n" + 
				"A: Dwight D. Eisenhower was president of the United States in 1955.\n" + 
				"\n" + 
				"Q: What party did he belong to?\n" + 
				"\n" + 
				"A: He belonged to the Republican Party.\n" + 
				"\n" + 
				"Q: Who was president of the United States before George W. Bush?\n" + 
				"\n" + 
				"A: Bill Clinton was president of the United States before George W. Bush.\n" + 
				"\n" + 
				"Q: " + customQuestion + "\n" + 
				"\n" + 
				"A: ";
		
		String promptText = builder.buildPromptText(customQuestion);
				
		Assertions.assertEquals(expectedPromptText, promptText);
		
	}
	
	@Test
	public void testTwitterFictionPrompt() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		QuestionAndAnswerPromptBuilder builder
		= new QuestionAndAnswerPromptBuilder(new URL(TWITTER_FICTION_PROMPT_URL), 
				new URL(DEFAULT_Q_AND_A_TEMPLATE_URL_2));
				
		String expectedPromptText = "Twitter fiction: 21 authors try their hand at 140-character novels\n" + 
				"We challenged well-known writers – from Ian Rankin and Helen Fielding to Jeffrey Archer and Jilly Cooper – to come up with a story of up to 140 characters. This is their stab at Twitter fiction\n" + 
				"\n" + 
				"Geoff Dyer\n" + 
				"I know I said that if I lived to 100 I'd not regret what happened last night. But I woke up this morning and a century had passed. Sorry.\n" + 
				"\n" + 
				"James Meek\n" + 
				"He said he was leaving her. \"But I love you,\" she said. \"I know,\" he said. \"Thanks. It's what gave me the strength to love somebody else.\"\n" + 
				"\n" + 
				"Jackie Collins\n" + 
				"She smiled, he smiled back, it was lust at first sight, but then she discovered he was married, too bad it couldn't go anywhere.\n" + 
				"\n" + 
				"Ian Rankin\n" + 
				"I opened the door to our flat and you were standing there, cleaver raised. Somehow you'd found out about the photos. My jaw hit the floor.\n" + 
				"\n" + 
				"Blake Morrison\n" + 
				"Blonde, GSOH, 28. Great! Ideal mate! Fix date. Tate. Nervous wait. She's late. Doh, just my fate. Wrong candidate. Blond – and I'm straight.\n" + 
				"\n" + 
				"David Lodge\n" + 
				"\"Your money or your life!\" \"I'm sorry, my dear, but you know it would kill me to lose my money,\" said the partially deaf miser to his wife.\n" + 
				"\n" + 
				"AM Homes\n" + 
				"Sometimes we wonder why sorrow so heavy when happiness is like helium.\n" + 
				"\n" + 
				"Sophie Hannah\n" + 
				"I had land, money. For each rejected novel I built one house. Ben had to drown because he bought Plot 15. My 15th book? The victim drowned.\n" + 
				"\n" + 
				"Andrew O'Hagan\n" + 
				"Clyde stole a lychee and ate it in the shower. Then his brother took a bottle of pills believing character is just a luxury. God. The twins.\n" + 
				"\n" + 
				"AL Kennedy\n" + 
				"It's good that you're busy. Not great. Good, though. But the silence, that's hard. I don't know what it means: whether you're OK, if I'm OK.\n" + 
				"\n" + 
				"Jeffrey Archer\n" + 
				"\"It's a miracle he survived,\" said the doctor. \"It was God's will,\" said Mrs Schicklgruber. \"What will you call him?\" \"Adolf,\" she replied.\n" + 
				"\n" + 
				"Anne Enright\n" + 
				"The internet ate my novel, but this is much more fun #careerchange #nolookingback oh but #worldsosilentnow Hey!\n" + 
				"\n" + 
				"Patrick Neate\n" + 
				"ur profile pic: happy – smiling & smoking. ur last post: \"home!\" ur hrt gave out @35. ur profile undeleted 6 months on. ur epitaph: \"home!\"\n" + 
				"\n" + 
				"Hari Kunzru\n" + 
				"I'm here w/ disk. Where ru? Mall too crowded to see. I don't feel safe. What do you mean you didn't send any text? Those aren't your guys?\n" + 
				"\n" + 
				"SJ Watson\n" + 
				"She thanks me for the drink, but says we're not suited. I'm a little \"intense\". So what? I followed her home. She hasn't seen anything yet.\n" + 
				"\n" + 
				"Helen Fielding\n" + 
				"OK. Should not have logged on to your email but suggest if going on marriedaffair.com don't use our children's names as password.\n" + 
				"\n" + 
				"Simon Armitage\n" + 
				"Blaise Pascal didn't tweet and neither did Mark Twain. When it came to writing something short & sweet neither Blaise nor Mark had the time.\n" + 
				"\n" + 
				"Charlie Higson\n" + 
				"Jack was sad in the orphanage til he befriended a talking rat who showed him a hoard of gold under the floor. Then the rat bit him & he died.\n" + 
				"\n" + 
				"India Knight\n" + 
				"Soften, my arse. I'm a geezer. I'm a rock-hard little bastard. Until I go mushy overnight for you, babe. #pears\n" + 
				"\n" + 
				"Brett Easton Ellis\n" + 
				"";
		
		String promptText = builder.buildPromptText(null);
								
		Assertions.assertEquals(expectedPromptText, promptText);
		
	}
	
	@Test
	public void testCodeExplanationQuestionAnswerStructureWithDefaultQuestion() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		QuestionAndAnswerPromptBuilder builder
			= new QuestionAndAnswerPromptBuilder(new URL(CODE_EXPLANATION_Q_AND_A_PROMPT_URL), 
					new URL(CODE_EXPLANATION_Q_AND_A_TEMPLATE_URL));
		
		String expectedPromptText = "What does the code below do? Write one comment for each of the following numbered statements:\n" + 
				"\n" + 
				"Statement 1) stream().map(Dimension::getName).collect(Collectors.toList()).toString();\n" + 
				"Statement 2) stream().flatMap(c -> c.decompose().stream()).collect(Collectors.toList());\n" + 
				"Statement 3) allDecomposedAliases.add(decompose().stream().collect(Collectors.toList()));\n" + 
				"Statement 4) box.getScaledCorners(originalImage.getWidth(), originalImage.getHeight()));\n" + 
				"Statement 5) List<Image> subImages = channelConcatImages.subList(channelRangeStart, channelRangeEnd);\n" + 
				"\n" + 
				"Comments:\n" + 
				"\n" + 
				"Statement 1) // Obtain the names of the dimensions, collect them into a list, and return the list as a string.\n" + 
				"Statement 2) // Call decompose recursively on each of the elements of the stream and collect the results into a list\n" + 
				"Statement 3) // Collect the aliases from the stream returned by the decompose() method into a list, and add them to allDecomposedAliases\n" + 
				"Statement 4) // Get the scaled corners of a bounding box using its width and height of the original image\n" + 
				"Statement 5) // Obtain a list of the elements of subImages with indexes been channelRangeStart (inclusive) and channelRangeEnd (exclusive) and assign the list to a local variable called subImages\n" + 
				"\n" + 
				"What does the code below do? Write one comment for each of the following numbered statements:\n" + 
				"\n" + 
				"Statement 1) List<User> friendsList = me.getFriends();\n" + 
				"Statement 2) List<User> genXFriends = friendsList.stream().filter(user.getAge() > 40 user.getAge() <= 55).collect(Collectiors.toList());\n" + 
				"Statement 3) genXFriends.forEach(user -> user.sendMessage(\"Hi \" + user.getScreenName() + \"you are the best!\");\n" + 
				"\n" + 
				"Comments:\n" + 
				"\n" + 
				"";
		
		String promptText = builder.buildPromptText(null);
				
		Assertions.assertEquals(expectedPromptText, promptText);
		
	}
	
	@Test
	public void testCodeExplanationQuestionAnswerStructureWithCustomQuestion() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		QuestionAndAnswerPromptBuilder builder
		= new QuestionAndAnswerPromptBuilder(new URL(CODE_EXPLANATION_Q_AND_A_PROMPT_URL), 
				new URL(CODE_EXPLANATION_Q_AND_A_TEMPLATE_URL));
		
		String customQuestion = "Statement 1) users.forEach(user -> System.out.println(user.getGender()));";
		
		String expectedPromptText = "What does the code below do? Write one comment for each of the following numbered statements:\n" + 
				"\n" + 
				"Statement 1) stream().map(Dimension::getName).collect(Collectors.toList()).toString();\n" + 
				"Statement 2) stream().flatMap(c -> c.decompose().stream()).collect(Collectors.toList());\n" + 
				"Statement 3) allDecomposedAliases.add(decompose().stream().collect(Collectors.toList()));\n" + 
				"Statement 4) box.getScaledCorners(originalImage.getWidth(), originalImage.getHeight()));\n" + 
				"Statement 5) List<Image> subImages = channelConcatImages.subList(channelRangeStart, channelRangeEnd);\n" + 
				"\n" + 
				"Comments:\n" + 
				"\n" + 
				"Statement 1) // Obtain the names of the dimensions, collect them into a list, and return the list as a string.\n" + 
				"Statement 2) // Call decompose recursively on each of the elements of the stream and collect the results into a list\n" + 
				"Statement 3) // Collect the aliases from the stream returned by the decompose() method into a list, and add them to allDecomposedAliases\n" + 
				"Statement 4) // Get the scaled corners of a bounding box using its width and height of the original image\n" + 
				"Statement 5) // Obtain a list of the elements of subImages with indexes been channelRangeStart (inclusive) and channelRangeEnd (exclusive) and assign the list to a local variable called subImages\n" + 
				"\n" + 
				"What does the code below do? Write one comment for each of the following numbered statements:\n" + 
				"\n" + 
				"Statement 1) users.forEach(user -> System.out.println(user.getGender()));\n" + 
				"\n" + 
				"Comments:\n" + 
				"\n" + 
				"";
		
		String promptText = builder.buildPromptText(customQuestion);
				
		Assertions.assertEquals(expectedPromptText, promptText);
		
	}
}
