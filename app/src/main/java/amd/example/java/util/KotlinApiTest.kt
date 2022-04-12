package amd.example.java.util

import amd.example.commonlibrary.util.CommonLog

/**
 * @author Created by on LvJP 2022/4/12
 *
 */
class KotlinApiTest {

    companion object {
        fun ListTest() {
            val list = mutableListOf<Int>()
            list.add(1)
            list.add(10)
            CommonLog.e("aa:$list")
        }
    }
}