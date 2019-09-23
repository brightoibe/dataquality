/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.datamigration;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Months;

/**
 * @author The Bright
 */
public class Test {
	
	public static void main(String[] arg) {
		int months = Months.monthsBetween(new DateTime(new Date()), new DateTime(new Date(12, 2, 2017))).getMonths();
		System.out.println("Months " + months);
	}
}
