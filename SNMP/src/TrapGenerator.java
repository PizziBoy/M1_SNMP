public class TrapGenerator implements Runnable {
	
	private enum TrapMode {
		/**
		 * Check if monitoredValue changed
		 */
		changingValue,
		/**
		 * check if monitoredValue is superior to numericValue
		 */
		isSuperior,
		/**
		 * check if monitoredValue is equal to numericValue
		 */
		isEqual,
		/**
		 * check if monitoredValue is inferior to numericValue
		 */
		isInferior
		;
	}	
	
	
	/**
	 * mib object in order to monitor mib's values (MUST exists in MIB)
	 */
	private MIB mib;
	
	private TrapMode mode;
	
	private String monitoredValue;
	
	private int numericValue;
	
	private boolean isSetupOK;
	
	

	
	public TrapGenerator(MIB mib) {
		super();
		this.mib = mib;
		this.isSetupOK = false;
	}

	//Optionnal
	public void retrieveMib(MIB mib) {
		this.mib = mib;
	}

	public void setMonitoredValue(String value) {
		this.monitoredValue = value;
	}
	
	/**
	 * 
	 * @param mode
	 * @param monitoredValue
	 * 
	 * Trap SETUP for changingValue mode
	 */
	public void setupTrapGenerator(TrapMode mode, String monitoredValue) {
		//check if monitoredValue exists on MIB register var else do not run
		if (this.mib.getValue(monitoredValue) == null || mode != TrapMode.changingValue) {
			System.out.println("Error in setup parameter, try again");
		} else {
			this.mode = mode;
			this.monitoredValue = monitoredValue;
			this.isSetupOK = true;
		}
	}
	
	/**
	 * 
	 * @param mode
	 * @param monitoredValue
	 * @param numericValue
	 * 
	 * Trap SETUP for other modes (changingValue excluded)
	 */
	public void setupTrapGenerator(TrapMode mode, String monitoredValue, int numericValue) {
		//check if monitoredValue exists on MIB register var else do not run
		if (this.mib.getValue(monitoredValue) == null) {
			System.out.println("Error in setup parameter, try again");
		}
		
		this.monitoredValue = monitoredValue;
		this.mode = mode;
		this.numericValue = numericValue;
		this.isSetupOK = true;
		
	}
	
	@Override
	public void run() {
		/**
		 * If value has been registered by setMonitorValue() AND exists on MIB
		 */
		if (this.isSetupOK) {
			//socket connection
			while (true) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.mib.getValue(monitoredValue);
				//If value changed
			}
		}
		
	}

}
