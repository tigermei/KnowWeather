package com.silencedut.weather_core.entity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tigermei on 2025/3/15 .
 * 和风天气接口
 * 用于解析和存储和风天气API返回的JSON数据
 */

// 下面是一段json数据，请格式化它
/*
{
        "code": "200",
        "updateTime": "2020-06-30T22:00+08:00",
        "fxLink": "http://hfx.link/2ax1",
        "now": {
            "obsTime": "2020-06-30T21:40+08:00",
            "temp": "24",
            "feelsLike": "26",
            "icon": "101",
            "text": "多云",
            "wind360": "123",
            "windDir": "东南风",
            "windScale": "1",
            "windSpeed": "3",
            "humidity": "72",
            "precip": "0.0",
            "pressure": "1003",
            "vis": "16",
            "cloud": "10",
            "dew": "21"
        },
        "refer": {
            "sources": [
                "QWeather",
                "NMC",
                "ECMWF"
            ],
            "license": [
                "QWeather Developers License"
            ]
        }
}
*/
//
//code 请参考状态码
//updateTime 当前API的最近更新时间
//fxLink 当前数据的响应式页面，便于嵌入网站或应用
//now.obsTime 数据观测时间
//now.temp 温度，默认单位：摄氏度
//now.feelsLike 体感温度，默认单位：摄氏度
//now.icon 天气状况的图标代码，另请参考天气图标项目
//now.text 天气状况的文字描述，包括阴晴雨雪等天气状态的描述
//now.wind360 风向360角度
//now.windDir 风向
//now.windScale 风力等级
//now.windSpeed 风速，公里/小时
//now.humidity 相对湿度，百分比数值
//now.precip 过去1小时降水量，默认单位：毫米
//now.pressure 大气压强，默认单位：百帕
//now.vis 能见度，默认单位：公里
//now.cloud 云量，百分比数值。可能为空
//now.dew 露点温度。可能为空
//refer.sources 原始数据来源，或数据源说明，可能为空
//refer.license 数据许可或版权声明，可能为空


/**
 * 和风天气数据实体类
 * 用于解析和存储和风天气API返回的天气数据
 */
public class HeWeatherV7 {
	@SerializedName(".")  // 表示将整个 JSON 对象映射到这个字段
	public HeWeather7Bean heWeather7;

	/**
	 * 获取和风天气数据对象
	 * @return 和风天气数据对象
	 */
	public HeWeather7Bean getHeWeather7() {
		return heWeather7;
	}

	/**
	 * 设置和风天气数据对象
	 * @param heWeather7 和风天气数据对象
	 */
	public void setHeWeather7(HeWeather7Bean heWeather7) {
		this.heWeather7 = heWeather7;
	}

	/**
	 * 和风天气数据Bean类
	 * 包含天气的详细信息
	 */
	public static class HeWeather7Bean {
		private String code;
		private String updateTime;
		private String fxLink;
		private NowBean now;
		private ReferBean refer;

		/**
		 * 获取状态码
		 * @return 状态码
		 */
		public String getCode() {
			return code;
		}

		/**
		 * 设置状态码
		 * @param code 状态码
		 */
		public void setCode(String code) {
			this.code = code;
		}

		/**
		 * 获取API的最近更新时间
		 * @return API的最近更新时间
		 */
		public String getUpdateTime() {
			return updateTime;
		}

		/**
		 * 设置API的最近更新时间
		 * @param updateTime API的最近更新时间
		 */
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}

		/**
		 * 获取当前数据的响应式页面链接
		 * @return 当前数据的响应式页面链接
		 */
		public String getFxLink() {
			return fxLink;
		}

		/**
		 * 设置当前数据的响应式页面链接
		 * @param fxLink 当前数据的响应式页面链接
		 */
		public void setFxLink(String fxLink) {
			this.fxLink = fxLink;
		}

		/**
		 * 获取当前天气数据
		 * @return 当前天气数据
		 */
		public NowBean getNow() {
			return now;
		}

		/**
		 * 设置当前天气数据
		 * @param now 当前天气数据
		 */
		public void setNow(NowBean now) {
			this.now = now;
		}

		/**
		 * 获取数据引用信息
		 * @return 数据引用信息
		 */
		public ReferBean getRefer() {
			return refer;
		}

		/**
		 * 设置数据引用信息
		 * @param refer 数据引用信息
		 */
		public void setRefer(ReferBean refer) {
			this.refer = refer;
		}

		/**
		 * 当前天气数据Bean类
		 * 包含实时天气的详细信息
		 */
		public static class NowBean {
			private String obsTime;
			private String temp;
			private String feelsLike;
			private String icon;
			private String text;
			private String wind360;
			private String windDir;
			private String windScale;
			private String windSpeed;
			private String humidity;
			private String precip;
			private String pressure;
			private String vis;
			private String cloud;
			private String dew;

			/**
			 * 获取数据观测时间
			 * @return 数据观测时间
			 */
			public String getObsTime() {
				return obsTime;
			}

			/**
			 * 设置数据观测时间
			 * @param obsTime 数据观测时间
			 */
			public void setObsTime(String obsTime) {
				this.obsTime = obsTime;
			}

			/**
			 * 获取温度，默认单位：摄氏度
			 * @return 温度
			 */
			public String getTemp() {
				return temp;
			}

			/**
			 * 设置温度，默认单位：摄氏度
			 * @param temp 温度
			 */
			public void setTemp(String temp) {
				this.temp = temp;
			}

			/**
			 * 获取体感温度，默认单位：摄氏度
			 * @return 体感温度
			 */
			public String getFeelsLike() {
				return feelsLike;
			}

			/**
			 * 设置体感温度，默认单位：摄氏度
			 * @param feelsLike 体感温度
			 */
			public void setFeelsLike(String feelsLike) {
				this.feelsLike = feelsLike;
			}

			/**
			 * 获取天气状况的图标代码
			 * @return 天气状况的图标代码
			 */
			public String getIcon() {
				return icon;
			}

			/**
			 * 设置天气状况的图标代码
			 * @param icon 天气状况的图标代码
			 */
			public void setIcon(String icon) {
				this.icon = icon;
			}

			/**
			 * 获取天气状况的文字描述
			 * @return 天气状况的文字描述
			 */
			public String getText() {
				return text;
			}

			/**
			 * 设置天气状况的文字描述
			 * @param text 天气状况的文字描述
			 */
			public void setText(String text) {
				this.text = text;
			}

			/**
			 * 获取风向360角度
			 * @return 风向360角度
			 */
			public String getWind360() {
				return wind360;
			}

			/**
			 * 设置风向360角度
			 * @param wind360 风向360角度
			 */
			public void setWind360(String wind360) {
				this.wind360 = wind360;
			}

			/**
			 * 获取风向
			 * @return 风向
			 */
			public String getWindDir() {
				return windDir;
			}

			/**
			 * 设置风向
			 * @param windDir 风向
			 */
			public void setWindDir(String windDir) {
				this.windDir = windDir;
			}

			/**
			 * 获取风力等级
			 * @return 风力等级
			 */
			public String getWindScale() {
				return windScale;
			}

			/**
			 * 设置风力等级
			 * @param windScale 风力等级
			 */
			public void setWindScale(String windScale) {
				this.windScale = windScale;
			}

			/**
			 * 获取风速，公里/小时
			 * @return 风速
			 */
			public String getWindSpeed() {
				return windSpeed;
			}

			/**
			 * 设置风速，公里/小时
			 * @param windSpeed 风速
			 */
			public void setWindSpeed(String windSpeed) {
				this.windSpeed = windSpeed;
			}

			/**
			 * 获取相对湿度，百分比数值
			 * @return 相对湿度
			 */
			public String getHumidity() {
				return humidity;
			}

			/**
			 * 设置相对湿度，百分比数值
			 * @param humidity 相对湿度
			 */
			public void setHumidity(String humidity) {
				this.humidity = humidity;
			}

			/**
			 * 获取过去1小时降水量，默认单位：毫米
			 * @return 过去1小时降水量
			 */
			public String getPrecip() {
				return precip;
			}

			/**
			 * 设置过去1小时降水量，默认单位：毫米
			 * @param precip 过去1小时降水量
			 */
			public void setPrecip(String precip) {
				this.precip = precip;
			}

			/**
			 * 获取大气压强，默认单位：百帕
			 * @return 大气压强
			 */
			public String getPressure() {
				return pressure;
			}

			/**
			 * 设置大气压强，默认单位：百帕
			 * @param pressure 大气压强
			 */
			public void setPressure(String pressure) {
				this.pressure = pressure;
			}

			/**
			 * 获取能见度，默认单位：公里
			 * @return 能见度
			 */
			public String getVis() {
				return vis;
			}

			/**
			 * 设置能见度，默认单位：公里
			 * @param vis 能见度
			 */
			public void setVis(String vis) {
				this.vis = vis;
			}

			/**
			 * 获取云量，百分比数值
			 * @return 云量
			 */
			public String getCloud() {
				return cloud;
			}

			/**
			 * 设置云量，百分比数值
			 * @param cloud 云量
			 */
			public void setCloud(String cloud) {
				this.cloud = cloud;
			}

			/**
			 * 获取露点温度
			 * @return 露点温度
			 */
			public String getDew() {
				return dew;
			}

			/**
			 * 设置露点温度
			 * @param dew 露点温度
			 */
			public void setDew(String dew) {
				this.dew = dew;
			}
		}

		/**
		 * 数据引用信息Bean类
		 * 包含数据来源和许可信息
		 */
		public static class ReferBean {
			private List<String> sources;
			private List<String> license;

			/**
			 * 获取原始数据来源
			 * @return 原始数据来源列表
			 */
			public List<String> getSources() {
				return sources;
			}

			/**
			 * 设置原始数据来源
			 * @param sources 原始数据来源列表
			 */
			public void setSources(List<String> sources) {
				this.sources = sources;
			}

			/**
			 * 获取数据许可或版权声明
			 * @return 数据许可或版权声明列表
			 */
			public List<String> getLicense() {
				return license;
			}

			/**
			 * 设置数据许可或版权声明
			 * @param license 数据许可或版权声明列表
			 */
			public void setLicense(List<String> license) {
				this.license = license;
			}
		}
	}
}
