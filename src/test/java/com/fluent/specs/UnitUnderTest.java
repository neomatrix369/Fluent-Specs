package com.fluent.specs;

public class UnitUnderTest
{
	private final Collaborator collaborator;

	public UnitUnderTest(final Collaborator collaborator)
	{
		this.collaborator = collaborator;
	}

	public UnitUnderTest()
	{
		this(new Collaborator()
		{

			public int doSomethingWith(final int i)
			{
				return 0;
			}

			public void doSomethingElse()
			{}
		});
	}

	public String doSomething()
	{
		return "DOES SOMETHING";
	}

	public void doSomethingElse()
	{}

	public void doSomethingWith(final int anyParameter)
	{
		collaborator.doSomethingWith(anyParameter);
	}
}