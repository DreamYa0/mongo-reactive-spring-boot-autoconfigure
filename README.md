# Reactive Mongodb数据库操作

如果需要使用mongodb数据库首先需要在 common模块的pom.xml文件中加入如下maven坐标

```xml
<dependency>
    <groupId>com.g7.framework</groupId>
    <artifactId>mongo-reactive-spring-boot-autoconfigure</artifactId>
</dependency>
```

**实体类**

```java
/**
 * @author dreamyao
 * @title 操作日志文档
 * @date 2021/10/25 2:08 下午
 * @since 1.0.0
 */
@Document("log_oplog")
@JsonIgnoreProperties({"id"})
public class OpLogDoc extends AbstractDoc<String>  implements Serializable {
 
    private static final long serialVersionUID = -1788778938009411621L;
 
    /**
     * 消息ID（全局唯一ID，可以用来防重复提交）
     */
    @Field("message_id")
    private String messageId;
    /**
     * traceId用例做链路日志查询用
     */
    private String traceId;
    /**
     * 日志类型
     */
    @Field("op_type")
    private String opType;
 
    /**
     * 操作类型(指操作场景)
     */
    @Field("log_type")
    private String logType;
 
    /**
     * 数据是否已发送到财务中台
     */
    @Field("is_send")
    private Boolean isSend = false;
 
    /**
     * 操作人
     */
    @Field("operator")
    private String operator;
 
    /**
     * 操作时间
     */
    @Field("op_time")
    private Date opTime;
 
    /**
     * 机构编码
     */
    @Field("orgcode")
    private String orgcode;
 
    /**
     * 货主机构编码
     */
    @Field("shipper_orgcode")
    private String shipperOrgcode;
 
    /**
     * 运单ID
     */
    @Field("waybill_id")
    private String waybillId;
 
    /**
     * 基地标识
     */
    @Field("base_code")
    private String baseCode;
 
    /**
     * 运单支付状态
     */
    @Field("waybill_pay_status")
    private String waybillPayStatus;
 
    /**
     * 变更信息
     */
    @Field("context")
    private ContextDoc context;
 
    /**
     * 运单信息
     */
    @Field("waybill")
    private WaybillDoc waybill;
 
    /**
     * 订单信息
     */
    @Field("order")
    private OrderDoc order;
 
    /**
     * 基地信息
     */
    @Field("platform")
    private PlatformDoc platform;
 
    /**
     * 承运人信息
     */
    @Field("carrier")
    private CarrierDoc carrier;
 
    /**
     * 货主信息
     */
    @Field("shipper")
    private ShipperDoc shipper;
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getMessageId() {
        return messageId;
    }
 
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
 
    public String getTraceId() {
        return traceId;
    }
 
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
 
    public String getOpType() {
        return opType;
    }
 
    public void setOpType(String opType) {
        this.opType = opType;
    }
 
    public String getLogType() {
        return logType;
    }
 
    public void setLogType(String logType) {
        this.logType = logType;
    }
 
    public String getOperator() {
        return operator;
    }
 
    public void setOperator(String operator) {
        this.operator = operator;
    }
 
    public Date getOpTime() {
        return opTime;
    }
 
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
 
    public String getOrgcode() {
        return orgcode;
    }
 
    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }
 
    public String getShipperOrgcode() {
        return shipperOrgcode;
    }
 
    public void setShipperOrgcode(String shipperOrgcode) {
        this.shipperOrgcode = shipperOrgcode;
    }
 
    public String getWaybillId() {
        return waybillId;
    }
 
    public void setWaybillId(String waybillId) {
        this.waybillId = waybillId;
    }
 
    public String getBaseCode() {
        return baseCode;
    }
 
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
 
    public ContextDoc getContext() {
        return context;
    }
 
    public void setContext(ContextDoc context) {
        this.context = context;
    }
 
    public WaybillDoc getWaybill() {
        return waybill;
    }
 
    public void setWaybill(WaybillDoc waybill) {
        this.waybill = waybill;
    }
 
    public OrderDoc getOrder() {
        return order;
    }
 
    public void setOrder(OrderDoc order) {
        this.order = order;
    }
 
    public PlatformDoc getPlatform() {
        return platform;
    }
 
    public void setPlatform(PlatformDoc platform) {
        this.platform = platform;
    }
 
    public CarrierDoc getCarrier() {
        return carrier;
    }
 
    public void setCarrier(CarrierDoc carrier) {
        this.carrier = carrier;
    }
 
    public ShipperDoc getShipper() {
        return shipper;
    }
 
    public void setShipper(ShipperDoc shipper) {
        this.shipper = shipper;
    }
 
    public Boolean getSend() {
        return isSend;
    }
 
    public void setSend(Boolean send) {
        isSend = send;
    }
 
    public String getWaybillPayStatus() {
        return waybillPayStatus;
    }
 
    public void setWaybillPayStatus(String waybillPayStatus) {
        this.waybillPayStatus = waybillPayStatus;
    }
}
```

## repository操作方式

```kotlin
/**
 * @author dreamyao
 * @title
 * @date 2021/11/9 8:23 下午
 * @since 1.0.0
 */
interface OpLogDocRepository : ReactiveMongoRepository<OpLogDoc, String> {
 
    fun findOplogDocByMessageId(messageId: String): Mono<OpLogDoc>
    fun countOplogDocByMessageId(messageId: String): Mono<Long>
    fun findOplogDocByWaybillId(waybillId: String): Mono<OpLogDoc>
    fun existsOpLogDocByMessageId(messageId: String): Mono<Boolean>
}
```

## template操作方式

也可以采用注入 ReactiveMongoTemplate 方式来进行mongodb的操作

## 官方参考文档 https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#preface