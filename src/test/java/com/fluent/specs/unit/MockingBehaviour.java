package com.fluent.specs.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.fluent.specs.Collaborator;

@SuppressWarnings("unused")
public class MockingBehaviour
{
	static final int ANY_RETURN = 1;
	static final int ANY_OTHER_RETURN = 2;
	static final int ANY_PARAMETER = 3;

	Collaborator collaborator;

	int integerOutcome;
	RuntimeException exceptionOutcome;
	RuntimeException exceptionToBeThrown;

	@Test public void should_stub_out_method_to_return_several_values() throws Exception
	{
		CONTEXT: collaborator = mock(Collaborator.class);
		CONTEXT: new FluentMockitoMockStubbing<Integer>(when(collaborator.doSomethingWith(ANY_PARAMETER))).RETURNS(ANY_RETURN,
				ANY_OTHER_RETURN);

		TRIGGER: integerOutcome = collaborator.doSomethingWith(ANY_PARAMETER);
		OUTCOME: assertThat(integerOutcome, is(equalTo(ANY_RETURN)));

		TRIGGER: integerOutcome = collaborator.doSomethingWith(ANY_PARAMETER);
		OUTCOME: assertThat(integerOutcome, is(equalTo(ANY_OTHER_RETURN)));
	}

	@Test public void should_stub_out_method_to_return_value() throws Exception
	{
		CONTEXT: collaborator = mock(Collaborator.class);
		CONTEXT: new FluentMockitoMockStubbing<Integer>(when(collaborator.doSomethingWith(ANY_PARAMETER))).RETURNS(ANY_RETURN);

		TRIGGER: integerOutcome = collaborator.doSomethingWith(ANY_PARAMETER);

		OUTCOME: assertThat(integerOutcome, is(equalTo(ANY_RETURN)));
	}

	@Test public void should_stub_out_method_to_throw_exception() throws Exception
	{
		CONTEXT: collaborator = mock(Collaborator.class);
		CONTEXT: exceptionToBeThrown = new RuntimeException();
		CONTEXT: new FluentMockitoMockStubbing<Integer>(when(collaborator.doSomethingWith(ANY_PARAMETER))).THROWS(exceptionToBeThrown);

		TRIGGER: try
		{
			collaborator.doSomethingWith(ANY_PARAMETER);
		}
		catch (final RuntimeException throwable)
		{
			exceptionOutcome = throwable;
		}

		OUTCOME: assertThat(exceptionOutcome, is(equalTo(exceptionToBeThrown)));
	}

	@Test public void should_stub_out_method_to_throw_several_exceptions() throws Exception
	{
		CONTEXT: collaborator = mock(Collaborator.class);
		CONTEXT: exceptionToBeThrown = new RuntimeException("SECOND");
		CONTEXT: new FluentMockitoMockStubbing<Integer>(when(collaborator.doSomethingWith(ANY_PARAMETER))).THROWS(new RuntimeException(
				"FIRST"), exceptionToBeThrown);

		TRIGGER: try
		{
			try
			{
				collaborator.doSomethingWith(ANY_PARAMETER);
			}
			catch (final Exception e)
			{}

			collaborator.doSomethingWith(ANY_PARAMETER);
		}
		catch (final RuntimeException throwable)
		{
			exceptionOutcome = throwable;
		}

		OUTCOME: assertThat(exceptionOutcome, is(equalTo(exceptionToBeThrown)));
	}
}
