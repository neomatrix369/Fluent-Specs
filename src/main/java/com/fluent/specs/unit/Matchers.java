package com.fluent.specs.unit;

import static java.lang.String.format;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Matchers
{
	protected Matchers()
	{}

	public static Matcher<String> match(final String regex)
	{
		return new Matches(regex, false);
	}

	@SafeVarargs public static <ITEM> Matcher<Iterable<ITEM>> haveItems(final Matcher<? super ITEM>... matchers)
	{
		return org.hamcrest.Matchers.hasItems(matchers);
	}

	public static Matcher<String> matchIgnoringCase(final String regex)
	{
		return new Matches(regex, true);
	}

	private static class Matches extends TypeSafeMatcher<String>
	{
		private final String regex;
		private final boolean ignoringCase;

		private Matches(final String regex, final boolean ignoringCase)
		{
			this.regex = regex;
			this.ignoringCase = ignoringCase;
		}

		public void describeTo(final Description description)
		{
			description.appendText(format("matching regex [%s] %s", regex, ignoringCase ? "ignoring case" : ""));
		}

		public boolean matchesSafely(final String expected)
		{
			return ignoringCase ? expected.toUpperCase().matches(regex.toUpperCase()) : expected.matches(regex);
		}
	}
}
