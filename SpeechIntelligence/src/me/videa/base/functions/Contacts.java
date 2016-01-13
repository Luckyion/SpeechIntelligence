package me.videa.base.functions;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;

public class Contacts {
	
	
	private static final String TAG = "Contacts";
	
	Context mContext;
	
	public Contacts(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	
	
	public String getContacts(){
		StringBuilder stringBuilder = null;
		Uri uri = Uri.parse("content://com.android.contacts/contacts");
		ContentResolver resolver = mContext.getContentResolver();
		Cursor cursor = resolver.query(uri, 
				new String[]{"_id"}, null, null, null);
		while(cursor.moveToNext()){
			int contractId = cursor.getInt(0);
			stringBuilder = new StringBuilder("contractID==");
			stringBuilder.append(contractId);
			uri = Uri.parse("content://com.android.contacts/contacts/"
					+ contractId + "/data");
			Cursor cursor2 = resolver.query(uri, 
					new String[]{"mimetype", "data1", "data2"}, null, null, null);
			while(cursor2.moveToNext()){
				String data1 = cursor2.getString(cursor2.getColumnIndex("data1"));
				String mimeType = cursor2.getString(cursor2.getColumnIndex("mimeType"));
				if ("vnd.android.cursor.item/name".equals(mimeType)) { //是姓名
					stringBuilder.append(",name=" + data1);
                } else if ("vnd.android.cursor.item/email_v2".equals(mimeType)) { //邮箱
                	stringBuilder.append(",email=" + data1);
                } else if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) { //手机
                	stringBuilder.append(",phone=" + data1);
                } 
			}
			cursor2.close();
			Log.i(TAG, stringBuilder.toString());
		}
		cursor.close();
		if(stringBuilder != null){
			return stringBuilder.toString();
		}
		return null;
	}
	
	/**
	 * 查询指定电话的联系人姓名，邮箱
	 * @throws Exception
	 */
    public String searchContactNameByNumber(String number) throws Exception {
        String name = null;
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + number);
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"display_name"}, null, null, null);
        if (cursor.moveToFirst()) {
            name = cursor.getString(0);
            Log.i(TAG, name);
        }
        cursor.close();
        return name;
    }
    
    /**
     * 查询指定联系人电话，邮箱
     * @param name
     * @return
     */
    public String searchContactNumberByName(String name){
    	String number = null;
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + name);
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{Phone.NUMBER}, null, null, null);
//        Cursor cursor = resolver.query(uri, new String[]{"display_number"}, null, null, null);
        if (cursor.moveToFirst()) {
        	number = cursor.getString(0);
            Log.i(TAG, number);
        }
        cursor.close();
        return number;
    }
    
    /**
     * 添加联系人，使用事务
     * @param name
     * @param phoneNo
     * @param email
     * @throws Exception
     */
    public void addContact(String name, String phoneNo, String email) throws Exception {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = mContext.getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
            .withValue("account_name", null)
            .build();
        operations.add(op1);
        
        uri = Uri.parse("content://com.android.contacts/data");
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
            .withValueBackReference("raw_contact_id", 0)
            .withValue("mimetype", "vnd.android.cursor.item/name")
            .withValue("data2", name)
            .build();
        operations.add(op2);
        
        ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
            .withValueBackReference("raw_contact_id", 0)
            .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
            .withValue("data1", phoneNo)            
            .withValue("data2", "2")
            .build();
        operations.add(op3);
        
        ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri)
        .withValueBackReference("raw_contact_id", 0)
        .withValue("mimetype", "vnd.android.cursor.item/email_v2")
        .withValue("data1", email)            
        .withValue("data2", "2")
        .build();
        operations.add(op4);
        
        resolver.applyBatch("com.android.contacts", operations);
    }
	

}

