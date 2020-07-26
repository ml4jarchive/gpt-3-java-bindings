package org.ml4j.gpt3.prompt.builders.questionanswer;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ml4j.gpt3.prompt.PromptSubmission;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class QuestionAndAnswerPromptBuilderSubmissionTest {
	
	private final String DEFAULT_Q_AND_A_PROMPT_URL = 
			"https://raw.githubusercontent.com/ml4j/gtp-3-prompt-templates/master/question-answer/"
			+ "default/examples/example1/question_answer_example_prompt_1.json";
	
	private final String TWITTER_FICTION_PROMPT_URL = 
			"https://raw.githubusercontent.com/ml4j/gpt-scrolls/master/tweets/twitter-fiction-prompt.json";

	
	private final String DEFAULT_Q_AND_A_TEMPLATE_URL = "https://raw.githubusercontent.com/ml4j/gtp-3-prompt-templates/master/question-answer/"
			+ "default/templates/question_answer_template_1.json";
	
	
	private final String DEFAULT_Q_AND_A_TEMPLATE_URL_2 = "https://raw.githubusercontent.com/ml4j/gtp-3-prompt-templates/master/question-answer/"
			+ "default/templates/question_answer_template_2.json";
				
	@Test
	public void testDefaultQuestionAnswerStructureWithDefaultQuestion() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		QuestionAndAnswerPromptBuilder builder
			= new QuestionAndAnswerPromptBuilder(new URL(DEFAULT_Q_AND_A_PROMPT_URL), 
					new URL(DEFAULT_Q_AND_A_TEMPLATE_URL));
		
		
		QuestionAndAnswerPromptSubmissionBuilder submissionBuilder
		 	= new QuestionAndAnswerPromptSubmissionBuilder(builder, null).withAnswerProviderName("testAnswerProvider"); 
		
		
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
		
		
		PromptSubmission promptSubmission = submissionBuilder.build();
						
		Assertions.assertEquals(expectedPromptText, promptSubmission.getInitialPromptText());
		Assertions.assertEquals(DEFAULT_Q_AND_A_PROMPT_URL, promptSubmission.getPromptId());
		Assertions.assertEquals(DEFAULT_Q_AND_A_TEMPLATE_URL, promptSubmission.getTemplateId());
		Assertions.assertEquals(expectedPromptText, submissionBuilder.getCurrentPromptText());

		Assertions.assertTrue(promptSubmission.getSubmissionResults().isEmpty());
		
		submissionBuilder.withSubmission("0.1", 100, "The Atlanta Braves won the World Series in 1995.\n\nQ:");
		
		Assertions.assertEquals(expectedPromptText, promptSubmission.getInitialPromptText());
		Assertions.assertEquals(DEFAULT_Q_AND_A_PROMPT_URL, promptSubmission.getPromptId());
		Assertions.assertEquals(DEFAULT_Q_AND_A_TEMPLATE_URL, promptSubmission.getTemplateId());
		Assertions.assertEquals(1, promptSubmission.getSubmissionResults().size());
		Assertions.assertEquals("testAnswerProvider", promptSubmission.getSubmissionResults().get(0).getSubmittedTo());
		Assertions.assertEquals("The Atlanta Braves won the World Series in 1995.\n\nQ:", 
				promptSubmission.getSubmissionResults().get(0).getResponseText());
		Assertions.assertEquals(100, 
				promptSubmission.getSubmissionResults().get(0).getRequest().getMaxTokens());
		Assertions.assertEquals(new BigDecimal("0.1"), 
				promptSubmission.getSubmissionResults().get(0).getRequest().getTemperature());
		
		String expectedSubmissionText = "Q: What is human life expectancy in the United States?\n" + 
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
				"A: The Atlanta Braves won the World Series in 1995.\n" + 
				"\n" + 
				"Q:";
		
		Assertions.assertEquals(expectedSubmissionText, submissionBuilder.getCurrentPromptText());

	}
	
	
	@Test
	public void testDefaultQuestionAnswerStructureWithDefaultQuestion2() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		
		QuestionAndAnswerPromptBuilder builder
			= new QuestionAndAnswerPromptBuilder(new URL(TWITTER_FICTION_PROMPT_URL), 
					new URL(DEFAULT_Q_AND_A_TEMPLATE_URL_2));
				
		QuestionAndAnswerPromptSubmissionBuilder submissionBuilder
		 	= new QuestionAndAnswerPromptSubmissionBuilder(builder, null).withAnswerProviderName("AIDungeon"); 
		
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
		
		
		PromptSubmission promptSubmission = submissionBuilder.build();
						
		Assertions.assertEquals(expectedPromptText, promptSubmission.getInitialPromptText());
		Assertions.assertEquals(TWITTER_FICTION_PROMPT_URL, promptSubmission.getPromptId());
		Assertions.assertEquals(DEFAULT_Q_AND_A_TEMPLATE_URL_2, promptSubmission.getTemplateId());
		Assertions.assertEquals(expectedPromptText, submissionBuilder.getCurrentPromptText());

		Assertions.assertTrue(promptSubmission.getSubmissionResults().isEmpty());
		
		submissionBuilder.withSubmission("1.1", 100, 
				"My name is Mary Ann. I'm an American girl who has been living in London "
				+ "since she was 13 years old. I've always wanted to be famous, but I never "
				+ "thought it would happen to me... until now.");
		
		Assertions.assertEquals(expectedPromptText, promptSubmission.getInitialPromptText());
		Assertions.assertEquals(TWITTER_FICTION_PROMPT_URL, promptSubmission.getPromptId());
		Assertions.assertEquals(DEFAULT_Q_AND_A_TEMPLATE_URL_2, promptSubmission.getTemplateId());
		Assertions.assertEquals(1, promptSubmission.getSubmissionResults().size());
		Assertions.assertEquals("AIDungeon", promptSubmission.getSubmissionResults().get(0).getSubmittedTo());
		Assertions.assertEquals("My name is Mary Ann. I'm an American girl who has been living in London since she was 13 years old. "
				+ "I've always wanted to be famous, but I never thought it would happen to me... until now.", 
				promptSubmission.getSubmissionResults().get(0).getResponseText());
		Assertions.assertEquals(100, 
				promptSubmission.getSubmissionResults().get(0).getRequest().getMaxTokens());
		Assertions.assertEquals(new BigDecimal("1.1"), 
			promptSubmission.getSubmissionResults().get(0).getRequest().getTemperature());
		
		String expectedSubmissionText = "Twitter fiction: 21 authors try their hand at 140-character novels\n" + 
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
				"My name is Mary Ann. I'm an American girl who has been living in London since she was 13 years old. I've always wanted to be famous, but I never thought it would happen to me... until now.";
		
		Assertions.assertEquals(expectedSubmissionText, submissionBuilder.getCurrentPromptText());
		
	}
}
