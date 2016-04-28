package org.tempuri;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkWSSoap soap = new LinkWS().getLinkWSSoap(); 
		System.out.println(soap.send("wyk","12322","13438223639","��ã����Ź����ѵ��Գɹ���������", "",""));
          //��ӡ��0 ��ʾ���Գɹ���}��Сʱ�ڲ�Ҫ��ͬһ���ֻ�����ͬ����.��
		  //������ͬ��
	}

}
