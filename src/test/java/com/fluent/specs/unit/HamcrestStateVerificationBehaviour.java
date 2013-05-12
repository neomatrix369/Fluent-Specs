package com.fluent.specs.unit;

import static org.hamcrest.Matchers.equalTo;
import static com.fluent.specs.unit.Matchers.match;

import org.junit.Test;

public class HamcrestStateVerificationBehaviour
{
	@Test public void should_equals_match_same_value_of_same_type()
	{
		new Verification<Double>(1.).shouldBe(equalTo(1.));
	}

	@Test public void should_equals_match_same_value_of_sub_type()
	{
		new Verification<Object>(1.).shouldBe(equalTo(1.));
	}

	@Test public void should_match_expression_of_the_same_type()
	{
		new Verification<String>("STRING  ").should(match("\\w+\\s+"));
	}

	@Test public void should_not_equal_match_different_value_of_same_type()
	{
		new Verification<Double>(1.).shouldNotBe(equalTo(5.));
	}

	@Test public void should_not_equal_match_different_value_of_sub_type()
	{
		new Verification<Object>(1.).shouldNotBe(equalTo(5.));
	}

	@Test public void should_not_equals_match_value_of_an_unrelated_type()
	{
		new Verification<Object>(1.).shouldNotBe(equalTo("STRING"));
	}

	@Test public void should_not_match_expression_of_the_same_type()
	{
		new Verification<String>("STRING").shouldNot(match("\\w+\\s+"));
	}

}
