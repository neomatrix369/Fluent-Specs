package com.fluent.specs.unit;

import org.junit.Test;

public class StateVerificationBehaviour
{
	@Test public void should_match_same_value_of_same_type()
	{
		new Verification<Double>(1.).shouldBe(1.);
	}

	@Test public void should_match_same_value_of_sub_type()
	{
		new Verification<Object>(1.).shouldBe(1.);
	}

	@Test public void should_not_match_different_value_of_same_type()
	{
		new Verification<Double>(1.).shouldNotBe(5.);
	}

	@Test public void should_not_match_different_value_of_sub_type()
	{
		new Verification<Object>(1.).shouldNotBe(5.);
	}

	@Test public void should_not_match_value_of_an_unrelated_type()
	{
		new Verification<Object>(1.).shouldNotBe("STRING");
	}
}
