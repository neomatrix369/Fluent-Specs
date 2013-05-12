package com.fluent.specs.unit.integration;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fluent.specs.UnitUnderTest;
import com.fluent.specs.unit.AbstractSpec;

public class VerificationBehaviour
{
	AbstractSpec specs = new AbstractSpec(){};
	UnitUnderTest unit = new UnitUnderTest();
	Exception exceptionOutcome;

	@Test public void should_capture_return_value_for_first_verification() throws Exception
	{
		specs.WHEN(unit.doSomething());

		specs.THEN(specs.theOutcome).shouldBe(sameInstance(unit.doSomething()));
	}

	@Test public void should_capture_return_value_for_subsequent_verifications() throws Exception
	{
		specs.WHEN(unit.doSomething());

		specs.THEN(specs.theOutcome).shouldBe(sameInstance(unit.doSomething()));
		specs.AND(specs.theOutcome).shouldBe(sameInstance(unit.doSomething()));
	}

	@Test public void should_capture_return_value_of_last_trigger_event() throws Exception
	{
		specs.WHEN("some other return");
		specs.AND(unit.doSomething());

		specs.THEN(specs.theOutcome).shouldBe(sameInstance(unit.doSomething()));
	}

	@Test public void should_complain_if_subsequent_verification_used_without_first_verification() throws Exception
	{
		specs.WHEN(unit.doSomething());

		try
		{
			specs.AND(specs.theOutcome).shouldBe(sameInstance(unit.doSomething()));
		}
		catch (final Exception throwable)
		{
			exceptionOutcome = throwable;
		}

		assertThat(exceptionOutcome, is(instanceOf(UnsupportedOperationException.class)));
	}

}
