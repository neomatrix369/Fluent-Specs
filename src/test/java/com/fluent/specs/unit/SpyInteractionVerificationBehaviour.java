package com.fluent.specs.unit;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;

import com.fluent.specs.UnitUnderTest;

@SuppressWarnings("unused")
public class SpyInteractionVerificationBehaviour
{
	static final int ANY_RETURN = 1;
	static final int ANY_PARAMETER = 3;

	UnitUnderTest spy;
	Verification<UnitUnderTest> verification;
	Exception exception;

	@Before public void BACKGROUND()
	{
		spy = spy(new UnitUnderTest());
		verification = new Verification<UnitUnderTest>(spy);
	}

	@Test public void should_verify_a_single_invocation()
	{
		TRIGGER: spy.doSomethingWith(ANY_PARAMETER);

		OUTCOME: verification.should().doSomethingWith(ANY_PARAMETER);
	}

	@Test public void should_verify_more_than_one_invocation()
	{
		TRIGGER: spy.doSomethingWith(ANY_PARAMETER);
		TRIGGER: spy.doSomethingWith(ANY_PARAMETER);

		OUTCOME: verification.should(times(2)).doSomethingWith(ANY_PARAMETER);
	}

	@Test public void should_verify_no_invocations()
	{
		TRIGGER: spy.doSomethingElse();

		OUTCOME: verification.should(never()).doSomethingWith(ANY_PARAMETER);
	}
}
