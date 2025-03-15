package com.silencedut.weather_core.entity;

//
//{
//		"code": "200",
//		"updateTime": "2025-03-15T17:58+08:00",
//		"fxLink": "https://www.qweather.com/en/air/shenzhen-101280601.html",
//		"now": {
//		"pubTime": "2025-03-15T17:00+08:00",
//		"aqi": "16",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "9",
//		"pm2p5": "4",
//		"no2": "19",
//		"so2": "6",
//		"co": "0.8",
//		"o3": "50"
//		},
//		"station": [
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "通心岭子站",
//		"id": "CNA1356",
//		"aqi": "14",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "8",
//		"pm2p5": "3",
//		"no2": "20",
//		"so2": "5",
//		"co": "0.8",
//		"o3": "43"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "洪湖",
//		"id": "CNA1357",
//		"aqi": "15",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "7",
//		"pm2p5": "3",
//		"no2": "17",
//		"so2": "6",
//		"co": "0.5",
//		"o3": "47"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "华侨城",
//		"id": "CNA1358",
//		"aqi": "12",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "8",
//		"pm2p5": "3",
//		"no2": "23",
//		"so2": "6",
//		"co": "0.9",
//		"o3": "36"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "南海子站",
//		"id": "CNA1359",
//		"aqi": "12",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "6",
//		"pm2p5": "3",
//		"no2": "20",
//		"so2": "5",
//		"co": "0.8",
//		"o3": "37"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "盐田",
//		"id": "CNA1360",
//		"aqi": "22",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "9",
//		"pm2p5": "3",
//		"no2": "14",
//		"so2": "6",
//		"co": "0.6",
//		"o3": "70"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "龙岗",
//		"id": "CNA1361",
//		"aqi": "17",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "8",
//		"pm2p5": "3",
//		"no2": "12",
//		"so2": "5",
//		"co": "0.7",
//		"o3": "52"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "南澳",
//		"id": "CNA1363",
//		"aqi": "15",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "8",
//		"pm2p5": "3",
//		"no2": "28",
//		"so2": "6",
//		"co": "0.6",
//		"o3": "48"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "葵涌",
//		"id": "CNA1364",
//		"aqi": "23",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "6",
//		"pm2p5": "3",
//		"no2": "14",
//		"so2": "6",
//		"co": "0.8",
//		"o3": "72"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "梅沙",
//		"id": "CNA1365",
//		"aqi": "24",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "6",
//		"pm2p5": "3",
//		"no2": "11",
//		"so2": "6",
//		"co": "0.7",
//		"o3": "74"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "观澜",
//		"id": "CNA1366",
//		"aqi": "15",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "14",
//		"pm2p5": "3",
//		"no2": "16",
//		"so2": "6",
//		"co": "0.8",
//		"o3": "47"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "莲花",
//		"id": "CNA3305",
//		"aqi": "13",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "8",
//		"pm2p5": "3",
//		"no2": "22",
//		"so2": "5",
//		"co": "1.0",
//		"o3": "41"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "民治",
//		"id": "CNA3306",
//		"aqi": "13",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "11",
//		"pm2p5": "3",
//		"no2": "25",
//		"so2": "7",
//		"co": "0.9",
//		"o3": "38"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "横岗",
//		"id": "CNA3307",
//		"aqi": "16",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "12",
//		"pm2p5": "7",
//		"no2": "17",
//		"so2": "7",
//		"co": "0.5",
//		"o3": "50"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "坪山",
//		"id": "CNA3447",
//		"aqi": "17",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "11",
//		"pm2p5": "6",
//		"no2": "22",
//		"so2": "6",
//		"co": "0.7",
//		"o3": "53"
//		},
//		{
//		"pubTime": "2025-03-15T17:00+08:00",
//		"name": "西乡",
//		"id": "CNA3623",
//		"aqi": "11",
//		"level": "1",
//		"category": "优",
//		"primary": "NA",
//		"pm10": "10",
//		"pm2p5": "3",
//		"no2": "22",
//		"so2": "5",
//		"co": "0.9",
//		"o3": "31"
//		}
//		],
//		"refer": {
//		"sources": [
//		"中国环境监测总站 (CNEMC)"
//		],
//		"license": [
//		"CC BY-SA 4.0"
//		]
//		}
//		}
//


//
//code 请参考状态码
//updateTime 当前API的最近更新时间
//fxLink 当前数据的响应式页面，便于嵌入网站或应用
//now.pubTime 空气质量数据发布时间
//now.aqi 空气质量指数
//now.level 空气质量指数等级
//now.category 空气质量指数级别
//now.primary 空气质量的主要污染物，空气质量为优时，返回值为NA
//now.pm10 PM10
//now.pm2p5 PM2.5
//now.no2 二氧化氮
//now.so2 二氧化硫
//now.co 一氧化碳
//now.o3 臭氧
//station.name 监测站名称
//station.id 监测站ID
//station.pubTime 空气质量数据发布时间
//station.aqi 空气质量指数
//station.level 空气质量指数等级
//station.category 空气质量指数级别
//station.primary 空气质量的主要污染物，空气质量为优时，返回值为NA
//station.pm10 PM10
//station.pm2p5 PM2.5
//station.no2 二氧化氮
//station.so2 二氧化硫
//station.co 一氧化碳
//station.o3 臭氧
//refer.sources 原始数据来源，或数据源说明，可能为空
//refer.license 数据许可或版权声明，可能为空


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 空气质量数据实体类
 * 用于解析和存储和风天气空气质量API返回的JSON数据
 */
public class AqiEntityV7 {
	@SerializedName(".")  // 表示将整个 JSON 对象映射到这个字段
	public AqiV7Bean aqiV7;

	/**
	 * 获取空气质量数据对象
	 * @return 空气质量数据对象
	 */
	public AqiV7Bean getAqiV7() {
		return aqiV7;
	}

	/**
	 * 设置空气质量数据对象
	 * @param aqiV7 空气质量数据对象
	 */
	public void setAqiV7(AqiV7Bean aqiV7) {
		this.aqiV7 = aqiV7;
	}

	/**
	 * 空气质量数据Bean类
	 * 包含空气质量的详细信息
	 */
	public static class AqiV7Bean {
		private String code;
		private String updateTime;
		private String fxLink;
		private NowBean now;
		private List<StationBean> station;
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
		 * 获取当前空气质量数据
		 * @return 当前空气质量数据
		 */
		public NowBean getNow() {
			return now;
		}

		/**
		 * 设置当前空气质量数据
		 * @param now 当前空气质量数据
		 */
		public void setNow(NowBean now) {
			this.now = now;
		}

		/**
		 * 获取监测站列表数据
		 * @return 监测站列表数据
		 */
		public List<StationBean> getStation() {
			return station;
		}

		/**
		 * 设置监测站列表数据
		 * @param station 监测站列表数据
		 */
		public void setStation(List<StationBean> station) {
			this.station = station;
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
		 * 当前空气质量数据Bean类
		 * 包含空气质量的实时监测数据
		 */
		public static class NowBean {
			private String pubTime;
			private String aqi;
			private String level;
			private String category;
			private String primary;
			private String pm10;
			private String pm2p5;
			private String no2;
			private String so2;
			private String co;
			private String o3;

			/**
			 * 获取空气质量数据发布时间
			 * @return 空气质量数据发布时间
			 */
			public String getPubTime() {
				return pubTime;
			}

			/**
			 * 设置空气质量数据发布时间
			 * @param pubTime 空气质量数据发布时间
			 */
			public void setPubTime(String pubTime) {
				this.pubTime = pubTime;
			}

			/**
			 * 获取空气质量指数
			 * @return 空气质量指数
			 */
			public String getAqi() {
				return aqi;
			}

			/**
			 * 设置空气质量指数
			 * @param aqi 空气质量指数
			 */
			public void setAqi(String aqi) {
				this.aqi = aqi;
			}

			/**
			 * 获取空气质量指数等级
			 * @return 空气质量指数等级
			 */
			public String getLevel() {
				return level;
			}

			/**
			 * 设置空气质量指数等级
			 * @param level 空气质量指数等级
			 */
			public void setLevel(String level) {
				this.level = level;
			}

			/**
			 * 获取空气质量指数级别
			 * @return 空气质量指数级别
			 */
			public String getCategory() {
				return category;
			}

			/**
			 * 设置空气质量指数级别
			 * @param category 空气质量指数级别
			 */
			public void setCategory(String category) {
				this.category = category;
			}

			/**
			 * 获取空气质量的主要污染物
			 * 空气质量为优时，返回值为NA
			 * @return 空气质量的主要污染物
			 */
			public String getPrimary() {
				return primary;
			}

			/**
			 * 设置空气质量的主要污染物
			 * @param primary 空气质量的主要污染物
			 */
			public void setPrimary(String primary) {
				this.primary = primary;
			}

			/**
			 * 获取PM10数值
			 * @return PM10数值
			 */
			public String getPm10() {
				return pm10;
			}

			/**
			 * 设置PM10数值
			 * @param pm10 PM10数值
			 */
			public void setPm10(String pm10) {
				this.pm10 = pm10;
			}

			/**
			 * 获取PM2.5数值
			 * @return PM2.5数值
			 */
			public String getPm2p5() {
				return pm2p5;
			}

			/**
			 * 设置PM2.5数值
			 * @param pm2p5 PM2.5数值
			 */
			public void setPm2p5(String pm2p5) {
				this.pm2p5 = pm2p5;
			}

			/**
			 * 获取二氧化氮数值
			 * @return 二氧化氮数值
			 */
			public String getNo2() {
				return no2;
			}

			/**
			 * 设置二氧化氮数值
			 * @param no2 二氧化氮数值
			 */
			public void setNo2(String no2) {
				this.no2 = no2;
			}

			/**
			 * 获取二氧化硫数值
			 * @return 二氧化硫数值
			 */
			public String getSo2() {
				return so2;
			}

			/**
			 * 设置二氧化硫数值
			 * @param so2 二氧化硫数值
			 */
			public void setSo2(String so2) {
				this.so2 = so2;
			}

			/**
			 * 获取一氧化碳数值
			 * @return 一氧化碳数值
			 */
			public String getCo() {
				return co;
			}

			/**
			 * 设置一氧化碳数值
			 * @param co 一氧化碳数值
			 */
			public void setCo(String co) {
				this.co = co;
			}

			/**
			 * 获取臭氧数值
			 * @return 臭氧数值
			 */
			public String getO3() {
				return o3;
			}

			/**
			 * 设置臭氧数值
			 * @param o3 臭氧数值
			 */
			public void setO3(String o3) {
				this.o3 = o3;
			}
		}

		/**
		 * 监测站数据Bean类
		 * 包含各个监测站的空气质量数据
		 */
		public static class StationBean {
			private String pubTime;
			private String name;
			private String id;
			private String aqi;
			private String level;
			private String category;
			private String primary;
			private String pm10;
			private String pm2p5;
			private String no2;
			private String so2;
			private String co;
			private String o3;

			/**
			 * 获取空气质量数据发布时间
			 * @return 空气质量数据发布时间
			 */
			public String getPubTime() {
				return pubTime;
			}

			/**
			 * 设置空气质量数据发布时间
			 * @param pubTime 空气质量数据发布时间
			 */
			public void setPubTime(String pubTime) {
				this.pubTime = pubTime;
			}

			/**
			 * 获取监测站名称
			 * @return 监测站名称
			 */
			public String getName() {
				return name;
			}

			/**
			 * 设置监测站名称
			 * @param name 监测站名称
			 */
			public void setName(String name) {
				this.name = name;
			}

			/**
			 * 获取监测站ID
			 * @return 监测站ID
			 */
			public String getId() {
				return id;
			}

			/**
			 * 设置监测站ID
			 * @param id 监测站ID
			 */
			public void setId(String id) {
				this.id = id;
			}

			/**
			 * 获取空气质量指数
			 * @return 空气质量指数
			 */
			public String getAqi() {
				return aqi;
			}

			/**
			 * 设置空气质量指数
			 * @param aqi 空气质量指数
			 */
			public void setAqi(String aqi) {
				this.aqi = aqi;
			}

			/**
			 * 获取空气质量指数等级
			 * @return 空气质量指数等级
			 */
			public String getLevel() {
				return level;
			}

			/**
			 * 设置空气质量指数等级
			 * @param level 空气质量指数等级
			 */
			public void setLevel(String level) {
				this.level = level;
			}

			/**
			 * 获取空气质量指数级别
			 * @return 空气质量指数级别
			 */
			public String getCategory() {
				return category;
			}

			/**
			 * 设置空气质量指数级别
			 * @param category 空气质量指数级别
			 */
			public void setCategory(String category) {
				this.category = category;
			}

			/**
			 * 获取空气质量的主要污染物
			 * 空气质量为优时，返回值为NA
			 * @return 空气质量的主要污染物
			 */
			public String getPrimary() {
				return primary;
			}

			/**
			 * 设置空气质量的主要污染物
			 * @param primary 空气质量的主要污染物
			 */
			public void setPrimary(String primary) {
				this.primary = primary;
			}

			/**
			 * 获取PM10数值
			 * @return PM10数值
			 */
			public String getPm10() {
				return pm10;
			}

			/**
			 * 设置PM10数值
			 * @param pm10 PM10数值
			 */
			public void setPm10(String pm10) {
				this.pm10 = pm10;
			}

			/**
			 * 获取PM2.5数值
			 * @return PM2.5数值
			 */
			public String getPm2p5() {
				return pm2p5;
			}

			/**
			 * 设置PM2.5数值
			 * @param pm2p5 PM2.5数值
			 */
			public void setPm2p5(String pm2p5) {
				this.pm2p5 = pm2p5;
			}

			/**
			 * 获取二氧化氮数值
			 * @return 二氧化氮数值
			 */
			public String getNo2() {
				return no2;
			}

			/**
			 * 设置二氧化氮数值
			 * @param no2 二氧化氮数值
			 */
			public void setNo2(String no2) {
				this.no2 = no2;
			}

			/**
			 * 获取二氧化硫数值
			 * @return 二氧化硫数值
			 */
			public String getSo2() {
				return so2;
			}

			/**
			 * 设置二氧化硫数值
			 * @param so2 二氧化硫数值
			 */
			public void setSo2(String so2) {
				this.so2 = so2;
			}

			/**
			 * 获取一氧化碳数值
			 * @return 一氧化碳数值
			 */
			public String getCo() {
				return co;
			}

			/**
			 * 设置一氧化碳数值
			 * @param co 一氧化碳数值
			 */
			public void setCo(String co) {
				this.co = co;
			}

			/**
			 * 获取臭氧数值
			 * @return 臭氧数值
			 */
			public String getO3() {
				return o3;
			}

			/**
			 * 设置臭氧数值
			 * @param o3 臭氧数值
			 */
			public void setO3(String o3) {
				this.o3 = o3;
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
