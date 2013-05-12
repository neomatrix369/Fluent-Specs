package com.fluent.specs.examples;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;

import org.junit.Test;
import org.mockito.Mock;

import com.fluent.specs.Collaborator;
import com.fluent.specs.UnitUnderTest;
import com.fluent.specs.unit.AbstractSpec;

@SuppressWarnings("unused")
public class ExampleSpecs extends AbstractSpec
{
	@Mock Collaborator collaborator;
	UnitUnderTest unitUnderTest;

	static final int ANY_RETURN = 1;
	static final int ANY_OTHER_RETURN = 2;
	static final int ANY_PARAMETER = 3;
	static final int ANY_OTHER_PARAMETER = 4;

	@Test public void example_showing_behaviour_verification_on_mock() throws Exception
	{
		GIVEN(collaborator.doSomethingWith(ANY_PARAMETER)).RETURNS(ANY_RETURN).AND(collaborator.doSomethingWith(ANY_OTHER_PARAMETER))
				.RETURNS(ANY_OTHER_RETURN);
		AND: unitUnderTest = new UnitUnderTest(collaborator);

		WHEN: unitUnderTest.doSomethingWith(ANY_PARAMETER);

		THEN(collaborator).should().doSomethingWith(ANY_PARAMETER);
	}

	@Test public void example_showing_state_verification_on_partial_mock() throws Exception
	{
		GIVEN_SPY(unitUnderTest = spy(new UnitUnderTest())).RETURNS("A NEW VALUE").ON().doSomething();

		WHEN(unitUnderTest.doSomething());

		THEN(theOutcome).shouldBe("A NEW VALUE");
		AND(theOutcome).shouldNotBe(new UnitUnderTest().doSomething());
	}

	@Test public void example_showing_state_verification() throws Exception
	{
		GIVEN: unitUnderTest = new UnitUnderTest(collaborator);

		WHEN(unitUnderTest.doSomething());

		THEN(theOutcome).shouldBe(equalTo(unitUnderTest.doSomething()));
	}

	@Test public void example_showing_behaviour_verification_on_spy() throws Exception
	{
		GIVEN: unitUnderTest = spy(new UnitUnderTest());

		WHEN(unitUnderTest.doSomething());

		THEN(unitUnderTest).should().doSomething();
		AND(unitUnderTest).should(never()).doSomethingElse();
	}
}
