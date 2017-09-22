package client.api.anxian.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gaoqichao on 15-9-24.
 * APP版本实体类
 */
@Data
public class VersionAnxian implements Serializable {
	@Id
	private Long id;

	/**
	 * 终端类型列表
	 */
	private String terminal;

	/**
	 * 设备
	 */
	private String device;

	/**
	 * 外部版本号
	 */
	private String outVerionid;

	/**
	 * 内部版本号
	 */
	private String innerVersionid;

	/**
	 * 是否强升
	 */
	private boolean isForcedUpdate = false;

	/**
	 * 升级描述
	 */
	private String updateDescription;

	/**
	 * app下载地址
	 */
	private String apkUrl;

	/**
	 * 创建人员
	 */
	private String userId;

	/**
	 * 创建时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdDate;

	/**
	 * 修改时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastModifiedDate;
}
