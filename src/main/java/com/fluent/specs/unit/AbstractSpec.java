package com.fluent.specs.unit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;

@SuppressWarnings("unchecked")
public abstract class AbstractSpec
{
	public Object theOutcome, it, theOutput, theReturn;
	private Step step;

	public <TYPE> Verification<TYPE> AND(final TYPE outcome)
	{
		if (step == Step.THEN)
		{
			return THEN(outcome);
		}
		else
		{
			WHEN(outcome);

			return (Verification<TYPE>) Verification.NULL;
		}
	}

	public <TYPE> AbstractSpec CALLING(final TYPE theOutcome)
	{
		return WHEN(theOutcome);
	}

	public <TYPE> InitialMocking<TYPE> GIVEN(final TYPE stubReturn)
	{
		return new FluentMockitoMockStubbing<TYPE>(when(stubReturn));
	}

	public <TYPE> SpyExpectation<TYPE> GIVEN_SPY(final TYPE spy)
	{
		return new FluentMockitoSpyStubbing<TYPE>(spy);
	}

	public <TYPE> InitialMocking<TYPE> IF(final TYPE stubReturn)
	{
		return GIVEN(stubReturn);
	}

	public <TYPE> SpyExpectation<TYPE> IF_SPY(final TYPE spy)
	{
		return GIVEN_SPY(spy);
	}

	@Before public void initMocks()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Before public void initStage()
	{
		step = Step.NONE;
		set(null);
	}

	protected void set(final Object value)
	{
		theOutcome = it = theOutput = theReturn = value;
	}

	public <TYPE> Verification<TYPE> So(final TYPE actual)
	{
		return THEN(actual);
	}

	public <TYPE> Verification<TYPE> THE_OUTPUT_OF(final TYPE theOutcome)
	{
		return WHEN(theOutcome).THEN(theOutcome);
	}

	public <TYPE> Verification<TYPE> THEN(final TYPE actual)
	{
		step = Step.THEN;
		return new Verification<TYPE>(actual);
	}

	public <TYPE> AbstractSpec WHEN(final TYPE theOutcome)
	{
		step = Step.WHEN;
		set(theOutcome);
		return this;
	}

	public <TYPE> TYPE WILL_CALL(final TYPE actual)
	{
		return THEN(actual).should();
	}

	public <TYPE> TYPE WILL_CALL(final VerificationMode mode, final TYPE actual)
	{
		return THEN(actual).should(mode);
	}

	public <TYPE> TYPE AND_ALSO(final VerificationMode mode, final TYPE actual)
	{
		return WILL_CALL(mode, actual);
	}

	public <TYPE> TYPE AND_ALSO(final TYPE actual)
	{
		return WILL_CALL(actual);
	}

	public static <TYPE> TYPE dummy(final Class<TYPE> aClass)
	{
		return mock(aClass);
	}

	public interface InitialMocking<A_TYPE>
	{
		public SubsequentMocking RETURNS(final A_TYPE firstReturnedValue, final A_TYPE... other);

		public SubsequentMocking THROWS(final Throwable... throwables);
	}

	public interface SpiedMethod<TYPE>
	{
		public TYPE ON();
	}

	public interface SpyExpectation<TYPE>
	{
		public SpiedMethod<TYPE> RETURNS(final Object returnValue, Object... otherReturnedValues);

		public SpiedMethod<TYPE> THROWS(final Exception throwable);
	}

	public interface SubsequentMocking
	{
		public <ANOTHER_TYPE> InitialMocking<ANOTHER_TYPE> AND(final ANOTHER_TYPE stubReturn);
	}

	private enum Step
	{
		GIVEN, WHEN, THEN, NONE
	}
}