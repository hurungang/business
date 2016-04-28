package com.runtech.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Constant {

	public static final String EXCEPTION = "exception";
	public static final String SESSION_USER = "user";

	public static final String SESSION_CHECK_SCRIPT = "CHECK_SCRIPT";
	public static final String INVOCATION = "invocation";
	public static final String REQUEST_URL = "requestURL";
	
	public static final String ACTION_ADD = "add";
	public static final String ACTION_ADD_CHILD = "addChild";
	public static final String ACTION_DELETE_CHILD = "deleteChild";
	public static final String ACTION_LIST = "list";
	public static final String ACTION_SAVE = "save";
	public static final String ACTION_SEND = "send";
	public static final String ACTION_SAVE_ALL = "saveAll";
	public static final String ACTION_SPECIAL_SAVE = "specialSave";
	public static final String ACTION_DELETE = "delete";
	public static final String ACTION_FINISH = "finish";
	public static final String ACTION_CANCEL = "cancel";
	public static final String ACTION_DUPLICATE = "duplicate";
	public static final String ACTION_APPROVE = "approve";
	public static final String ACTION_PEND_ORDER = "pendOrder";
	public static final String ACTION_PREPARE = "prepare";
	public static final String ACTION_DELIVER = "deliver";
	public static final String ACTION_CANCEL_ORDER = "cancelOrder";
	public static final String ACTION_REJECT_ORDER = "rejectOrder";
	public static final String ACTION_SUCCEED_ORDER = "succeedOrder";
	public static final String ACTION_CONFIRM = "confirm";
	public static final String ACTION_CONFIRM_ORDER = "confirmOrder";
	public static final String ACTION_SPECIAL_DELETE = "specialDelete";
	public static final String ACTION_WITHDRAW = "withdraw";
	public static final String ACTION_REPORT = "report";
	public static final String ACTION_DUPLICATE_ALL = "duplicateAll";
	public static final String ACTION_CANCEL_CHILD = "cancelChild";
	public static final String ACTION_RESTORE_CHILD = "restoreChild";
	public static final String ACTION_RESTORE_ORDER = "restoreOrder";
	public static final String ACTION_SAVE_CHILD = "saveChild";
	public static final String ACTION_CONVERT_TO_PAY_ON_ARRIVAL = "convertToPayOnArrival";
	public static final String ACTION_PUBLISH = "publish";
	public static final String ACTION_REJECT = "reject";
	public static final String ACTION_APPROVE_PUBLISH = "approvePublish";
	public static final String ACTION_REJECT_PUBLISH = "rejectPublish";
	public static final String ACTION_CREATE = "create";
	public static final String ACTION_CONSUME = "consume";

	public static final String SYMBOL_ID_SPLIT = "_";
	public static final String SYMBOL_COMMA_SPLIT = ",";
	
	public static final String PARAMETER_LOGIN = "login";
	public static final String PARAMETER_NAME = "name";
	public static final String PARAMETER_PASSWORD = "password";
	
	public static final String STATUS_APPROVED = "approved";
	public static final String STATUS_ENABLED = "enabled";
	public static final String STATUS_DISABLED = "disabled";
	public static final String STATUS_SUCCEED = "succeed";
	public static final String STATUS_DRAFT = "draft";
	public static final String STATUS_FAILED = "failed";
	public static final String STATUS_USED = "used";
	public static final String STATUS_UNUSED = "unused";
	public static final String STATUS_SOLD_OUT = "soldOut";

	public static final String STATUS_PENDING = "pending";
	public static final String STATUS_UNCONFIRMED = "unconfirmed";
	public static final String STATUS_CONFIRMED = "confirmed";
	public static final String STATUS_PENDING_PAY = "pendingPay";
	public static final String STATUS_PAID = "paid";
	public static final String STATUS_PREPARING = "preparing";
	public static final String STATUS_DELIVERING = "delivering";
	public static final String STATUS_DELIVERED = "delivered";
	public static final String STATUS_CANCELLED = "cancelled";
	public static final String STATUS_REJECTED = "rejected";
	public static final String STATUS_WITHDRAWED = "withdrawed";
	public static final String STATUS_DELETED = "deleted";
	public static final String STATUS_MANUALED = "manualed";
	public static final String STATUS_PENDING_PUBLISH = "pendingPublish";
	public static final String STATUS_PUBLISH_REJECTED = "publishRejected";
	
	public static final String CODE_MODULE_ROOT = "root";
	public static final String CODE_MERCHANT_MODULE_ROOT = "merchantCategory";
	public static final String CODE_SITE_MODULE_ROOT = "mainV2";
	public static final String CODE_ORDER_TYPE = "orderType";
	public static final String CODE_PAY_TYPE = "payType";
	public static final String CODE_DELIVERY_AREA = "deliveryArea";
	public static final String CODE_COMMODITY_AREA = "commodityArea";
	public static final String CODE_COMMODITY_TYPE = "commodityType";
	public static final String CODE_MERCHANT_AREA = "merchantArea";
	public static final String CODE_ACTIVITY_AREA = "activityArea";
	public static final String CODE_UNIT_TYPE = "unitType";
	public static final String CODE_PROMOTION_TYPE = "promotionType";
	public static final String CODE_PAGE_COMPONENT_TYPE = "pageComponentType";
	public static final String CODE_PROMOTION_SPECIAL_FLAG = "promotionSpecialFlag";
	public static final String CODE_PROMOTION_STATUS = "promotionStatus";
	public static final String CODE_PICTURE_CATEGORY = "pictureCategory";
	public static final String CODE_RECOMMEND_TYPE = "recommendType";
	public static final String CODE_AREA_CATEGORY = "areaCategory";
	public static final String CODE_CONTRACT_TYPE = "contractType";
	public static final String CODE_COUPON_STATUS = "couponStatus";
	public static final String CODE_SEX = "sex";
	public static final String CODE_CREDENTIAL_TYPE = "credentialType";
	public static final String CODE_OPPORTUNITY_STATUS = "opportunityStatus";
	
	public static final String PAY_BY_COUPON = "payByCoupon";
	public static final String PAY_BY_POINT = "payByPoint";
	public static final String PAY_BY_ALIPAY = "payByAlipay";
	public static final String PAY_ON_ARRIVAL = "payOnArrival";

	public static final String ORDER_TYPE_POINT_EXCHANGE= "pointExchange";
	public static final String ORDER_TYPE_POINT_AUCTION= "pointAuction";
	public static final String RESULT_ALIPAY_FAIL = "fail";
	public static final String RESULT_ALIPAY_SUCCESS = "success";
	
	public static final String CODE_BUYER_PAY = "BUYER_PAY";
	public static final String CODE_EXPRESS = "EXPRESS";
	public static final String CODE_VIRTUAL = "VIRTUAL";
	public static final String CODE_COMMODITY_CATEGORY_ROOT = "tuanCategory";
	public static final String CODE_CONTENT_CATEGORY_ROOT = "content";
	public static final String CODE_HELP_CATEGORY_ROOT = "help";
	public static final String CODE_RECOMMEND_MAIL_TEMPLATE = "recommendMailTemplate";
	public static final String CODE_EXTEND_PICTURE = "extendedPicture";
	public static final String CODE_BASIC_PICTURE = "basicPicture";
	
	public static final String STATUS_WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
	public static final String STATUS_WAIT_BUYER_CONFIRM_GOODS = "WAIT_BUYER_CONFIRM_GOODS";
	public static final String STATUS_TRADE_FINISHED = "TRADE_FINISHED";
	
	public static final List<String> STATUS_LIST_PAYMENT_SUCCESS = Arrays.asList(STATUS_SUCCEED,STATUS_WAIT_SELLER_SEND_GOODS,STATUS_WAIT_BUYER_CONFIRM_GOODS,STATUS_TRADE_FINISHED);
	public static final String CODE_POINT_TYPE_ORDER = "normalPoint";
	public static final String CODE_POINT_TYPE_PROMOTIVE = "promotivePoint";
	public static final String CODE_POINT_TYPE_REWARD = "rewardPoint";
	public static final String CODE_POINT_TYPE_REFUND = "refundPoint";

	public static final Integer MERCHANT_ID_WEBSITE = 0;
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_TIME_PICKER = "yyyy-MM-dd HH:mm";
	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(FORMAT_DATE_TIME);
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(FORMAT_DATE);
	public static final DateFormat TIME_PICKER_FORMAT = new SimpleDateFormat(FORMAT_TIME_PICKER);
	public static Date DATE_NULL = null;
	public static final String STATUS_EXPIRED = "expired";
	static {
		try {
			DATE_NULL = DATE_TIME_FORMAT.parse("2000-00-00 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public static final String MODULE_TYPE_PAGE = "Page";
	public static final Object MODULE_TYPE_MODULE = "Module";
	public static final String RESULT_NULL = "NULL";
	public static final String SESSION_MERCHANT = "merchant";
	public static final String SESSION_RECOMMEND_USER = "recommendUser";
	public static final String CODE_FIND_PASSWORD_MAIL_TEMPLATE = "findPasswordMail";
	public static final String CODE_VALIDATE_MAIL_TEMPLATE = "validateMail";
	public static final String ATTRIBUTE_REGISTER_USER = "registerUser";
	public static final String CONTEXT_TYPE_DEFAULT = "text/html;charset=utf-8";
	public static final String SESSION_PICTURE_CODE = "rand";
	public static final String STATUS_READ = "read";
	public static final String STATUS_UNREAD = "unread";
	public static final String ATTRIBUTE_GROUP_USER	= "groupUser";
	public static final String COOKIE_GROUP_USER_ID = "groupUser.id";
	public static final String COOKIE_USER_ID = "user.id";
	public static final String STATUS_CACHE_ENABLED = "cacheEnabled";
	public static final String COOKIE_GROUP_CONTACT = "groupUser.contact";
	public static final String COOKIE_GROUP_CONTACT_PHONE = "groupUser.contactPhone";
	public static final String ORDER_TYPE_SELF_GROUP = "SELF_GROUP";
	public static final String RUNTECH_PROPERTIES = "/runtech.properties";
	public static final String ATTRIBUTE_REFERENCE_USER_ID = "reference_user_id";
	public static final String CODE_USER_TYPE_INDIVIDUAL = "individual";
	public static final String ORDER_TYPE_WEEKLY_GROUP = "WEEKLY_GROUP";
	public static final String CODE_END_USER_ROLE = "END_USER";
	public static final String ACTION_DEFAULT = "default";
	public static final String ACTION_CHANGE_PASSWORD = "changePassword";
	public static final String CODE_ERROR_PASSWORD = "error.password.wrongOldPassword";
	public static final String CODE_ERROR_NEW_PASSWORD = "error.password.wrongNewPassword";
	public static final String CODE_ERROR_SESSION_EXPIRE = "error.session.expire";
	public static final String ERROR_SESSION_EXPIRE = "error.session.expire";
	public static final String ACTION_UPDATE_ACCOUNT = "updateAccount";
	public static final String ACTION_DOWNLOAD = "download";
	public static final String ERROR_AUTHORIZATION = "error.common.authorization";
	public static final String ACTION_UPLOAD = "upload";
	public static final String ACTION_LOAD = "load";
	public static final String CODE_BUSINESS_AREA = "businessArea";
	public static final String CODE_DEFAULT_COMMODITY_DETAIL = "defaultCommodityDetail";
	public static final String ACTION_AGENT_ORDER = "agentOrder";
}
