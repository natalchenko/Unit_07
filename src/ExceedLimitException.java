
@SuppressWarnings("serial")
public class ExceedLimitException extends Exception {
	
	private int overLimit ;
	
	public ExceedLimitException(int value){
		super() ;
		this.overLimit = value ;
	}
	
	public void setOverLimit( int value ){
		overLimit = value;
	}
	
	public int getOverLimit(){
		return overLimit ; 
	}
}
