/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author phuonghoangnguyen
 */
public class StringHandle {

    public static boolean isNumber(String value) {
        if (!value.matches("\\d*")) {
            return false;
        }
        return true;
    }
}
