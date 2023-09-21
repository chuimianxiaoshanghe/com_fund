1.添加MySQL数据库连接
    主机：rm-bp19n5y607121p0xhqo.mysql.rds.aliyuncs.com
    端口：3306
    用户名：test1
    密码：Aa123456

2.idea中运行Java代码

3.测试接口
测试公司接口：
GET /companies          浏览器输入： localhost:8082/companies
GET /companies/{comId}  浏览器输入： localhost:8082/companies/1

以下接口需要使用到postman  请求地址为：localhost:8082/companies
POST /companies
    postman中设置请求体参数，Body->row->JSON 输入如下示例;
    {
        "orgUniCode": "200031312",
        "orgChiName": "广州富力地产股份有限公司",
        "induSmaPar": "房地产业",
        "orgDele": "李思廉",
        "regCap": 375236.7344,
        "orgEstDate": "1994-08-31T00:00:00"
    }

PUT /companies/{comId}  请求地址为：localhost:8082/companies/8
    postman中设置请求体参数，Body->row->JSON 输入如下示例;
    {
        "orgUniCode": "200362085",
        "orgChiName": "同方知网(北京)技术有限公司",
        "induSmaPar": "零售业",
        "orgDele": "刘长欣",
        "regCap": 100,
        "orgEstDate": "2004-11-18T00:00:00"
    }

DELETE /companies/{comId}  请求地址为：localhost:8082/companies/9


测试基金接口：     localhost:8082/funds?pageNum=1&pageSize=10&sortField=end_date&sortDirection=desc
    postman中设置params
GET /fund
pageNum  	    1
pageSize    	10
sortField	  	end_date
sortDirection	desc
