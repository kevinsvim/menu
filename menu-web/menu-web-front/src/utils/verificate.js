const verificate = {}

verificate.regexs= {
  phone:{
    pattern: /^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\d{8}$/,
    name:"手机号码"
  },
  mail:{
    pattern: /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
    name:"电子邮箱"
  },
  username:{
    pattern:/^[a-zA-Z\\u4E00-\\u9FA5][a-zA-Z0-9_\\u4E00-\\u9FA5]{4,10}$/,
    name:"登录账号"
  },
  cars:{
    pattern:/^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-HJ-NP-Z][A-HJ-NP-Z0-9]{4,5}[A-HJ-NP-Z0-9挂学警港澳]$/,
    name:"车牌号"
  },
  password:{
    pattern:/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$)([^\u4e00-\u9fa5\s]){6,20}$/,
    name:"密码",
    message:"请输入6-20位英文字母、数字或者符号（除空格），且字母、数字和标点符号至少包含两种"
  }
}

verificate.validate = {
  //验证手机号
  username(rule, value, callback) {
    if(!verificate.regexs.username.pattern.test(value)){
      return callback(new Error('登录账号不能使用特殊字符,4到10个字符'))
    }
    callback()
  },
  //验证手机号
  phone(rule, value, callback) {
    if(!verificate.regexs.phone.pattern.test(value)){
      return callback(new Error('请输入正确的手机号码'))
    }
    callback()
  },
  //验证邮箱
  mail(rule, value, callback) {
    if(!verificate.regexs.mail.pattern.test(value)){
      return callback(new Error('请输入正确的邮箱'))
    }
    callback()
  },
  //车牌号码
  cars(rule, value, callback) {
    if(!verificate.regexs.cars.pattern.test(value)){
      return callback(new Error('请输入正确的车牌号码'))
    }
    callback()
  },
  //密码验证
  password(rule, value, callback) {
    if(!verificate.regexs.password.pattern.test(value)){
      return callback(new Error(verificate.regexs.password.message))
    }
    callback()
  },
}

export default verificate
