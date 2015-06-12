package sup.hoon.log.appender;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class Every5MinFileAppender extends FileAppender{
	private String defaultFolderPath;
	private String curDate;
	//아래 두개의 변수는 log4j설정파일에서 설정되어야함
	private String serverNo;
	private String serviceName;

	/**
	 * @param appendDirectory 상세경로명,마지막 / 는 하지않음
	 * @throws IOException 파일생성에러
	 */
	public Every5MinFileAppender(String appendDirectory,String loggerName,String appenderName) throws IOException {
		// TODO Auto-generated constructor stub
		SetAppender setAppender = (SetAppender)LogManager.getLogger(loggerName).getAppender(appenderName);

		StringBuilder sb = new StringBuilder();
		sb.append(setAppender.getFolderPath());
		sb.append(appendDirectory);
		sb.append("/");
		
		this.serverNo = setAppender.getServerNo();
		this.serviceName = setAppender.getServiceName();
		this.defaultFolderPath = sb.toString();
		setLayout(new PatternLayout("%m%n"));
		setName(appendDirectory);
		setFile(this.defaultFolderPath, true, bufferedIO, bufferSize);
	}
	@Override
	public String getFile() {
	    return fileName;
	  }
	
	@Override
	//최초한번만 호출
	public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
		this.defaultFolderPath = fileName;//다른 메서드에서 새파일을 만들때 기본경로를 알아야해서 전역변수에 할당
        super.setFile(getNewFileName(fileName,new SimpleDateFormat(("yyyyMMddHHmm")).format(new Date()).toString()), append, bufferedIO, bufferSize);
	  }	
	
	@Override
	protected void subAppend(LoggingEvent event) {
		String dateFormat = new SimpleDateFormat(("yyyyMMddHHmm")).format(new Date()).toString();
		//이 메서드는 로그가 추가될때마다 호출
		//일단 처음파일의 끝이 0 이나 5로 존재하기 떄문에 이전과 5분이상차이 나는 경우 파일을 새로생성해야함
		if((Long.valueOf(dateFormat)-Long.valueOf(this.curDate)>=5)){			
			try {
				this.closeFile();
				super.setFile(getNewFileName(this.defaultFolderPath,dateFormat), true, bufferedIO, bufferSize);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.subAppend(event);
	}

	public String getServerNo() {
		if(serverNo==null)
			return "00";
		return serverNo;
	}

	public void setServerNo(String serverNo) {
		this.serverNo = serverNo;
	}

	public String getServiceName() {
		if(serviceName==null)
			return "defaultservice";
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	private String getNewFileName(String curName,String dateFormat){
		StringBuilder newFileName = new StringBuilder();
		StringBuilder curDate = new StringBuilder();
		
		newFileName.append(curName);
		newFileName.append(dateFormat.substring(0, 8));
		newFileName.append("/");
		newFileName.append(serviceName);
		newFileName.append(".");
		newFileName.append(serverNo);
		newFileName.append(".");

        int min = Integer.valueOf(dateFormat.substring(dateFormat.length()-1));
        //매 5분마다 파일이 생기므로 파일명은 마지막이 00 또는 05 만 존재함
        //처음 호출시간은 0~9까지 모두 올 수 있으므로 모든 시간에서 00 또는 05로 일반화
        if(min==0||min==5){
        	curDate.append(dateFormat);
        }else if(0<min&&min<5){
        	curDate.append(dateFormat.substring(0, 11));
        	curDate.append("0");
        }else{
        	curDate.append(dateFormat.substring(0, 11));
        	curDate.append("5");
        }
        
        //마지막 생성시간을 기록
        //로그가 10분이상 차이날 경우 기존파일에 그대로 쓸 수도 있기때문에 비교를 위해 전역변수로 넣어줌
        this.curDate = curDate.toString();
		
        newFileName.append(curDate);
        newFileName.append(".log");
        
        return newFileName.toString();
	}
}
