package client.api.anxian.app.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.Date;

/**
 * Created by gaoqichao on 15-9-7.
 * 广告模块
 */
@Data
public class AdItemTempleteAnxian {
	@Id
	private Long id;

	/**
	 * 模板名称
	 */
	private String templeteName;

	/**
	 * 专区id
	 */
	private String zoneId;

	/**
	 * 模板说明
	 */
	private String description;

	/**
	 * 商品id
	 */
	private String sns;

	/**
	 * 创建时间
	 */
	private Date createdDate = new Date();

	/**
	 * 更新时间
	 */
	private Date updatedDate;
}
