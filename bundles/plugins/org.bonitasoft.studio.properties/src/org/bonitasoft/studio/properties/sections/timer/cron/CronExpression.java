package org.bonitasoft.studio.properties.sections.timer.cron;

import org.bonitasoft.studio.properties.i18n.Messages;



public class CronExpression {

	private String mode; //Minutes,Hourly,Daily,Weekly,Monthly or Yearly

	private int minuteFrequencyForMinute;
	private boolean useEveryHour;
	private int hourFrequencyForHourly;

	private boolean useEveryDayForDaily;
	private int dayFrequencyForDaily;
	
	private boolean onMonday;
	private boolean onTuesday;
	private boolean onWednesday;
	private boolean onThursday;
	private boolean onFriday;
	private boolean onSaturday;
	private boolean onSunday;
	
	private boolean useDayInMonthForMonthly;
	private int dayOfMonthForMonthly;
	private int monthInYearForMonthly;
	
	private int monthRankForMonthly;
	private int dayOfWeekForMonthly;
	private int monthOfYearForMonthly;
	
	private boolean everyYearForYearly;
	private int monthForYearly;
	private int dayOfMonthForYearly;
	
	private int monthRankForYearly;
	private int dayOfWeekForYearly;
	private int monthOfYearForYearly;
	
	private int atHour;
	private int atMinute;
	private int atSecond;
	
	private int atHourInDay;
	private int atMinuteInDay;
	private int atSecondInDay;
	
	private int atHourInMonth;
	private int atMinuteInMonth;
	private int atSecondInMonth;
	
	private int atHourInWeek;
	private int atMinuteInWeek;
	private int atSecondInWeek;
	
	private int atHourInYear;
	private int atMinuteInYear;
	private int atSecondInYear;
	
	public CronExpression(){

	}

	public String getExpression(){
		if(mode.equals(Messages.minutes)){
			return "0 0/"+minuteFrequencyForMinute+" * 1/1 * ? *" ; 
		}else if(mode.equals(Messages.hourly)){
			if(useEveryHour){
				return "0 0 0/"+hourFrequencyForHourly+" 1/1 * ? *" ; 
			}else{
				return atSecond+" "+atMinute+" "+atHour+" 1/1 * ? *";	
			}
		}else if(mode.equals(Messages.daily)){
			if(useEveryDayForDaily){
				return  atSecondInDay+" "+atMinuteInDay+" "+atHourInDay+" 1/"+dayFrequencyForDaily+" * ? *";	
			}else{
				return  atSecondInDay+" "+atMinuteInDay+" "+atHourInDay+" ? * MON-FRI *";	
			}
		}else if(mode.equals(Messages.weekly)){
			StringBuilder sb = new StringBuilder(atSecondInWeek+" "+atMinuteInWeek+" "+atHourInWeek+" ? * ");
			if(onMonday){
				sb.append("MON,");
			}
			if(onTuesday){
				sb.append("TUE,");
			}
			if(onWednesday){
				sb.append("WED,");
			}
			if(onThursday){
				sb.append("THU,");
			}
			if(onFriday){
				sb.append("FRI,");
			}
			if(onSaturday){
				sb.append("SAT,");
			}
			if(onSunday){
				sb.append("SUN,");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append(" *");
			return sb.toString();
		}else if(mode.equals(Messages.monthly)){
			if(useDayInMonthForMonthly){
				return  atSecondInMonth+" "+atMinuteInMonth+" "+atHourInMonth+" "+dayOfMonthForMonthly+" 1/"+monthInYearForMonthly+" ? *";	
			}else{
				String day = null;
				switch (dayOfWeekForMonthly) {
				case 1:day="MON";break;
				case 2:day="TUE";break;
				case 3:day="WED";break;
				case 4:day="THU";break;
				case 5:day="FRI";break;
				case 6:day="SAT";break;
				case 7:day="SUN";break;
				default:break;
				}
				if(day==null){
					throw new RuntimeException("Invalid day in week : "+dayOfWeekForMonthly);
				}
				return atSecondInMonth+" "+atMinuteInMonth+" "+atHourInMonth+" ? 1/"+monthOfYearForMonthly+" "+day+"#"+monthRankForMonthly+" *";	
			}
		}else if(mode.equals(Messages.yearly)){
			if(everyYearForYearly){
				return  atSecondInYear+" "+atMinuteInYear+" "+atHourInYear+" "+dayOfMonthForYearly+" "+monthForYearly+" ? *";	
			}else{
				String day = null;
				switch (dayOfWeekForYearly) {
				case 1:day="MON";break;
				case 2:day="TUE";break;
				case 3:day="WED";break;
				case 4:day="THU";break;
				case 5:day="FRI";break;
				case 6:day="SAT";break;
				case 7:day="SUN";break;
				default:break;
				}
				if(day==null){
					throw new RuntimeException("Invalid day in week : "+dayOfWeekForMonthly);
				}
				return  atSecondInYear+" "+atMinuteInYear+" "+atHourInYear+" ? "+monthOfYearForYearly+" "+day+"#"+monthRankForYearly+" *";	
			}
		}
		return null;
	}
	
	public int getMonthInYearForMonthly() {
		return monthInYearForMonthly;
	}

	public void setMonthInYearForMonthly(int monthInYearForMonthly) {
		this.monthInYearForMonthly = monthInYearForMonthly;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isUseEveryHour() {
		return useEveryHour;
	}

	public void setUseEveryHour(boolean useEveryHour) {
		this.useEveryHour = useEveryHour;
	}

	public int getMinuteFrequencyForMinute() {
		return minuteFrequencyForMinute;
	}

	public void setMinuteFrequencyForMinute(int minuteFrequencyForMinute) {
		this.minuteFrequencyForMinute = minuteFrequencyForMinute;
	}

	public int getHourFrequencyForHourly() {
		return hourFrequencyForHourly;
	}

	public void setHourFrequencyForHourly(int hourFrequencyForHourly) {
		this.hourFrequencyForHourly = hourFrequencyForHourly;
	}

	public boolean isUseEveryDayForDaily() {
		return useEveryDayForDaily;
	}

	public void setUseEveryDayForDaily(boolean useEveryDayForDaily) {
		this.useEveryDayForDaily = useEveryDayForDaily;
	}

	public int getDayFrequencyForDaily() {
		return dayFrequencyForDaily;
	}

	public void setDayFrequencyForDaily(int dayFrequencyForDaily) {
		this.dayFrequencyForDaily = dayFrequencyForDaily;
	}

	public boolean isOnMonday() {
		return onMonday;
	}

	public void setOnMonday(boolean onMonday) {
		this.onMonday = onMonday;
	}

	public boolean isOnTuesday() {
		return onTuesday;
	}

	public void setOnTuesday(boolean onTuesday) {
		this.onTuesday = onTuesday;
	}

	public boolean isOnWednesday() {
		return onWednesday;
	}

	public void setOnWednesday(boolean onWednesday) {
		this.onWednesday = onWednesday;
	}

	public boolean isOnThursday() {
		return onThursday;
	}

	public void setOnThursday(boolean onThursday) {
		this.onThursday = onThursday;
	}

	public boolean isOnFriday() {
		return onFriday;
	}

	public void setOnFriday(boolean onFriday) {
		this.onFriday = onFriday;
	}

	public boolean isOnSaturday() {
		return onSaturday;
	}

	public void setOnSaturday(boolean onSaturday) {
		this.onSaturday = onSaturday;
	}

	public boolean isOnSunday() {
		return onSunday;
	}

	public void setOnSunday(boolean onSunday) {
		this.onSunday = onSunday;
	}

	public boolean isUseDayInMonthForMonthly() {
		return useDayInMonthForMonthly;
	}

	public void setUseDayInMonthForMonthly(boolean useDayInMonthForMonthly) {
		this.useDayInMonthForMonthly = useDayInMonthForMonthly;
	}

	public int getDayOfMonthForMonthly() {
		return dayOfMonthForMonthly;
	}

	public void setDayOfMonthForMonthly(int dayOfMonthForMonthly) {
		this.dayOfMonthForMonthly = dayOfMonthForMonthly;
	}


	public int getMonthRankForMonthly() {
		return monthRankForMonthly;
	}

	public void setMonthRankForMonthly(int monthRankForMonthly) {
		this.monthRankForMonthly = monthRankForMonthly;
	}

	public int getDayOfWeekForMonthly() {
		return dayOfWeekForMonthly;
	}

	public void setDayOfWeekForMonthly(int dayOfWeekForMonthly) {
		this.dayOfWeekForMonthly = dayOfWeekForMonthly;
	}

	public int getMonthOfYearForMonthly() {
		return monthOfYearForMonthly;
	}

	public void setMonthOfYearForMonthly(int monthOfYearForMonthly) {
		this.monthOfYearForMonthly = monthOfYearForMonthly;
	}

	public boolean isEveryYearForYearly() {
		return everyYearForYearly;
	}

	public void setEveryYearForYearly(boolean everyYearForYearly) {
		this.everyYearForYearly = everyYearForYearly;
	}

	public int getMonthForYearly() {
		return monthForYearly;
	}

	public void setMonthForYearly(int monthForYearly) {
		this.monthForYearly = monthForYearly;
	}

	public int getDayOfMonthForYearly() {
		return dayOfMonthForYearly;
	}

	public void setDayOfMonthForYearly(int dayOfMonthForYearly) {
		this.dayOfMonthForYearly = dayOfMonthForYearly;
	}

	public int getMonthRankForYearly() {
		return monthRankForYearly;
	}

	public void setMonthRankForYearly(int monthRankForYearly) {
		this.monthRankForYearly = monthRankForYearly;
	}

	public int getDayOfWeekForYearly() {
		return dayOfWeekForYearly;
	}

	public void setDayOfWeekForYearly(int dayOfWeekForYearly) {
		this.dayOfWeekForYearly = dayOfWeekForYearly;
	}

	public int getMonthOfYearForYearly() {
		return monthOfYearForYearly;
	}

	public void setMonthOfYearForYearly(int monthOfYearForYearly) {
		this.monthOfYearForYearly = monthOfYearForYearly;
	}

	public int getAtHourInDay() {
		return atHourInDay;
	}

	public void setAtHourInDay(int atHourInDay) {
		this.atHourInDay = atHourInDay;
	}

	public int getAtMinuteInDay() {
		return atMinuteInDay;
	}

	public void setAtMinuteInDay(int atMinuteInDay) {
		this.atMinuteInDay = atMinuteInDay;
	}

	public int getAtHourInMonth() {
		return atHourInMonth;
	}

	public void setAtHourInMonth(int atHourInMonth) {
		this.atHourInMonth = atHourInMonth;
	}

	public int getAtMinuteInMonth() {
		return atMinuteInMonth;
	}

	public void setAtMinuteInMonth(int atMinuteInMonth) {
		this.atMinuteInMonth = atMinuteInMonth;
	}

	public int getAtHourInWeek() {
		return atHourInWeek;
	}

	public void setAtHourInWeek(int atHourInWeek) {
		this.atHourInWeek = atHourInWeek;
	}

	public int getAtMinuteInWeek() {
		return atMinuteInWeek;
	}

	public void setAtMinuteInWeek(int atMinuteInWeek) {
		this.atMinuteInWeek = atMinuteInWeek;
	}

	public int getAtHourInYear() {
		return atHourInYear;
	}

	public void setAtHourInYear(int atHourInYear) {
		this.atHourInYear = atHourInYear;
	}

	public int getAtMinuteInYear() {
		return atMinuteInYear;
	}

	public void setAtMinuteInYear(int atMinuteInYear) {
		this.atMinuteInYear = atMinuteInYear;
	}

	public int getAtHour() {
		return atHour;
	}

	public void setAtHour(int atHour) {
		this.atHour = atHour;
	}

	public int getAtMinute() {
		return atMinute;
	}

	public void setAtMinute(int atMinute) {
		this.atMinute = atMinute;
	}

	public int getAtSecond() {
		return atSecond;
	}

	public void setAtSecond(int atSecond) {
		this.atSecond = atSecond;
	}

	public int getAtSecondInDay() {
		return atSecondInDay;
	}

	public void setAtSecondInDay(int atSecondInDay) {
		this.atSecondInDay = atSecondInDay;
	}

	public int getAtSecondInMonth() {
		return atSecondInMonth;
	}

	public void setAtSecondInMonth(int atSecondInMonth) {
		this.atSecondInMonth = atSecondInMonth;
	}

	public int getAtSecondInWeek() {
		return atSecondInWeek;
	}

	public void setAtSecondInWeek(int atSecondInWeek) {
		this.atSecondInWeek = atSecondInWeek;
	}

	public int getAtSecondInYear() {
		return atSecondInYear;
	}

	public void setAtSecondInYear(int atSecondInYear) {
		this.atSecondInYear = atSecondInYear;
	}

}
