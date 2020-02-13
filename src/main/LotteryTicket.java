/**
 * 
 */
package main;

import java.util.Objects;
import java.util.Set;

/**
 * A lottery ticket, containing the numbers that will be checked to determine if the lottery was won
 * @author Alessandro Cavicchioli
 * @version 1.0
 */
public class LotteryTicket
{
	private final String ownerName;
	private final Set<Integer> numbers;
	
	/**
	 * Creates a new lottery ticket with the specified owner name and the specified set of numbers
	 * @param ownerName the name of the owner
	 * @param numbers the set of numbers on the ticket
	 */
	public LotteryTicket(String ownerName, Set<Integer> numbers)
	{
		this.ownerName = ownerName;
		this.numbers = numbers;
	}
	
	/**
	 * Returns the name of the owner of this ticket
	 * @return the name of the owner of this ticket
	 */
	public String getOwnerName()
	{
		return ownerName;
	}
	
	/**
	 * Returns the set of numbers on this ticket
	 * @return the set of numbers on this ticket
	 */
	public Set<Integer> getNumbers()
	{
		return numbers;
	}
}
