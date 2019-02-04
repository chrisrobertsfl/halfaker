package com.halfaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Stream;

public class Inquiry {

	@SuppressWarnings("unchecked")
	public static Inquiry create(Function<Inquiry, Inquiry>... setters) {
		return Stream.of(setters).reduce(setter -> setter, Function::andThen).apply(new Inquiry());
	}

	
	String question;
	List<String> validResponses = new ArrayList<>();

	public Inquiry question(String question) {
		this.question = question;
		return this;
	}

	public Inquiry addValidResponse(String validResponse) {
		this.validResponses.add(validResponse);
		return this;
	}

	public String inquire() {
		Scanner scanIn = new Scanner(System.in);
		String answer = null;
		if (scanIn.hasNext())
		{
			answer = new String(scanIn.nextLine());
		}
		scanIn.close();
		return answer;
	}
	
}
