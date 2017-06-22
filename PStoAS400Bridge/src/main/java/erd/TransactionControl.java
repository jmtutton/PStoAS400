package erd;

public class TransactionControl {
	
	/**
	 * Commit-Transaction from TranCtrl.SQC
	 */
	public static void commitTransaction() {
		//#ifdef debugx
		//    display 'Entering TRANCTRL.SQC: Commit-Transaction'
		//#endif
		//
		//#ifdef SYBASE
				//Begin-SQL On-Error=ErrCommit
					//COMMIT TRANSACTION
				//End-SQL
				//Begin-SQL
					//BEGIN TRANSACTION
				//End-SQL
		//#else 
				//#ifdef INFORMIX
					//Begin-SQL
						//COMMIT WORK
					//End-SQL
					//Begin-SQL
						//BEGIN WORK
					//End-SQL
				//#else
					//COMMIT
				//#endif
		//#endif
		//#ifdef debugx
		//    display 'Exiting TRANCTRL.SQC: Commit-Transaction'
		//#endif
	}
}
