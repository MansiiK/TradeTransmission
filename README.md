# TradeTransmission
I have written a logic using Java along with the Junits, to solve the Problem statement given in README file

Problem Statement

Consider a scenario where thousands of trades flows into one store. Assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order

Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired
T1	1	CP-1	B1	20/05/2020	<today date>	N
T2	2	CP-2	B1	20/05/2021	<today date>	N
T2	1	CP-1	B1	20/05/2021	14/03/2015	N
T3	3	CP-3	B2	20/05/2014	<today date>	Y


You would be required to include following validations in the program.

1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the     existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update the expire flag if in a store the trade crosses the maturity date.

