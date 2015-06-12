package sup.hoon.log.appender;

import org.apache.log4j.WriterAppender;

/**
 * 설정용으로 사용
 * 
 */
public class SetAppender extends WriterAppender {

	private String folderPath;
	private String serverNo;
	private String serviceName;
	
	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public String getServerNo() {
		return serverNo;
	}

	public void setServerNo(String serverNo) {
		this.serverNo = serverNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
