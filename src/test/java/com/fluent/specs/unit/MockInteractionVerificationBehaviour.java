package com.fluent.specs.unit;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.exceptions.misusing.NotAMockException;

import com.fluent.specs.Collaborator;

@SuppressWarnings("unused")
public class MockInteractionVerificationBehaviour
{
	static final int ANY_RETURN = 1;
	static final int ANY_PARAMETER = 3;

	Collaborator collaborator;
	Verification<Collaborator> verification;
	Exception exception;

	@Before public void BACKGROUND()
	{
		collaborator = aMockedCollaborator();
		verification = new Verification<Collaborator>(collaborator);
	}

	@Test public void should_complain_if_asked_to_verify_a_normal_object()
	{
		CONTEXT: collaborator = new Collaborator()
		{//[

			public void doSomethingElse() { }
			public int doSomethingWith(final int i) {return 0;}};//]

		CONTEXT: verification = new Verification<Collaborator>(collaborator);

		TRIGGER: try
		{
			collaborator.doSomethingWith(ANY_PARAMETER);
			verification.should().doSomethingWith(ANY_PARAMETER);
		}
		catch (final Exception exception)
		{
			this.exception = exception;
		}

		OUTCOME: assertThat(exception, is(instanceOf(NotAMockException.class)));
	}

	@Test public void should_verify_a_single_invocation()
	{
		TRIGGER: collaborator.doSomethingWith(ANY_PARAMETER);

		OUTCOME: verification.should().doSomethingWith(ANY_PARAMETER);
	}

	@Test public void should_verify_more_than_one_invocation()
	{
		TRIGGER: collaborator.doSomethingWith(ANY_PARAMETER);
		TRIGGER: collaborator.doSomethingWith(ANY_PARAMETER);

		OUTCOME: verification.should(times(2)).doSomethingWith(ANY_PARAMETER);
	}

	@Test public void should_verify_no_invocations()
	{
		TRIGGER: collaborator.doSomethingElse();

		OUTCOME: verification.should(never()).doSomethingWith(ANY_PARAMETER);
	}

	static Collaborator aMockedCollaborator()
	{
		return when(mock(Collaborator.class).doSomethingWith(ANY_PARAMETER)).thenReturn(ANY_RETURN).getMock();
	}
}
