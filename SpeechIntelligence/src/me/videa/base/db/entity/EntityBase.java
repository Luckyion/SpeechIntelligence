/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.videa.base.db.entity;

/**
 * Author: wyouflf
 * Date: 13813
 * Time: 涓婂崍11:15
 */
public abstract class EntityBase {


    //@Id // 濡傛灉涓婚敭娌℃湁鍛藉悕鍚嶄负id鎴朹id鐨勬椂锛岄渶瑕佷负涓婚敭娣诲姞姝ゆ敞瑙£
    //@NoAutoIncrement // int,long绫诲瀷鐨刬d榛樿®よ嚜澧烇紝涓嶆兂浣跨敤鑷ª澧炴椂娣诲姞姝ゆ敞瑙£
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

