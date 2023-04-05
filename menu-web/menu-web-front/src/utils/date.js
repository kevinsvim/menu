/**
 * dateTimeStamp是一个时间毫秒，注意时间戳是秒的形式，在这个毫秒的基础上除以1000，就是十位数的时间戳。13位数的都是时间毫秒。
 * @param dateTimeStamp
 * @returns {string}
 */
export function time_ago(dateTimeStamp){
  const minute = 1000 * 60;      //把分，时，天，周，半个月，一个月用毫秒表示
  const hour = minute * 60;
  const day = hour * 24;
  const week = day * 7;
  const half_month = day * 15;
  const month = day * 30;
  const now = new Date().getTime();   //获取当前时间毫秒
  console.log(now)
  const diffValue = now - dateTimeStamp;//时间差

  if(diffValue < 0){
    return;
  }
  const minC = diffValue / minute;  //计算时间差的分，时，天，周，月
  const hourC = diffValue / hour;
  const dayC = diffValue / day;
  const weekC = diffValue / week;
  const monthC = diffValue / month;
  let result = '';
  if(monthC >= 1 && monthC <= 3){
    result = " " + parseInt(monthC) + "月前"
  }else if(weekC >= 1 && weekC <= 3){
    result = " " + parseInt(weekC) + "周前"
  }else if(dayC >= 1 && dayC <= 6){
    result = " " + parseInt(dayC) + "天前"
  }else if(hourC >= 1 && hourC <= 23){
    result = " " + parseInt(hourC) + "小时前"
  }else if(minC >= 1 && minC <= 59){
    result =" " + parseInt(minC) + "分钟前"
  }else if(diffValue >= 0 && diffValue <= minute){
    result = "刚刚"
  }else {
    const datetime = new Date();
    datetime.setTime(dateTimeStamp);
    const N_year = datetime.getFullYear();
    const N_month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    const N_date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    const N_hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
    const N_minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    const N_second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    result = N_year + "-" + N_month + "-" + N_date
  }
  return result;
}
