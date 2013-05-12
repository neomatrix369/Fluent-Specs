package com.fluent.specs.unit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.verify;

import org.hamcrest.Matcher;
import org.mockito.verification.VerificationMode;

public class Verification<TYPE>
{
	protected final TYPE actual;
	public static final Verification<?> NULL = new NoVerification();

	public Verification(final TYPE actual)
	{
		this.actual = actual;
	}

	public TYPE should()
	{
		return verify(actual);
	}

	public <ANY extends TYPE> void should(final Matcher<ANY> expected)
	{
		shouldBe(expected);
	}

	public TYPE should(final VerificationMode times)
	{
		return verify(actual, times);
	}

	@SuppressWarnings("unchecked") public <ANY extends TYPE> void shouldBe(final Matcher<ANY> expected)
	{
		assertThat((ANY) actual, expected);
	}

	public void shouldBe(final TYPE expected)
	{
		assertThat(actual, is(expected));
	}

	public <ANY extends TYPE> void shouldNot(final Matcher<ANY> expected)
	{
		shouldBe(not(expected));
	}

	public <ANY extends TYPE> void shouldNotBe(final Matcher<ANY> expected)
	{
		shouldBe(not(expected));
	}

	public void shouldNotBe(final TYPE expected)
	{
		assertThat(actual, is(not(expected)));
	}

	@SuppressWarnings("rawtypes")
	private static final class NoVerification extends Verification<Object>
	{
		private NoVerification()
		{
			super(null);
		}

		public Object should()
		{
			complain();

			return this;
		}

		public void should(final Matcher expected)
		{
			complain();
		}

		public Object should(final VerificationMode times)
		{
			complain();

			return this;
		}

		public void shouldBe(final Matcher expected)
		{
			complain();
		}

		public void shouldBe(final Object expected)
		{
			complain();
		}

		public void shouldNot(final Matcher expected)
		{
			complain();
		}

		public void shouldNotBe(final Matcher expected)
		{
			complain();
		}

		public void shouldNotBe(final Object expected)
		{
			complain();
		}

		private static void complain()
		{
			throw new UnsupportedOperationException("You've tried to invoke verification on an THEN-AND step");
		}
	}

}