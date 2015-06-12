package sup.hoon.log.appender;

public class Every5MinFileAppenderTest{
	public void main(){
		try{
			org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("folderpath");
			if(!logger.getAllAppenders().hasMoreElements()){
				Every5MinFileAppender appender = new Every5MinFileAppender("folderpath","STAT","eachStatisticsFile");
				logger.addAppender(appender);
			}
			logger.info("");
			
		
		}catch(Exception e){
			System.out.println("Log Write Error!!");
			e.printStackTrace();
		}
	}
}
