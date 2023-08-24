package lab2;

public class Producer {

	public static void main(String[] args) throws Exception {
		String host = args[0];
		int port = Integer.parseInt(args[1]);

		AB ab = new NetAB(); // TODO zameniti null stvaranjem konkretnog objekta, npr. new PCClass(...)

		System.out.println("hi");
		if (!ab.init(host, port))
			return;

		System.out.println("cao");
		for (int i = 2; i < args.length; i++) {
			String name = args[i];
			System.out.println(name);
			Goods goods = new GoodsImpl(); // TODO zameniti null stvaranjem konkretnog objekta, npr. new GoodsClass(name)

			int size = (int) (Math.random() * 32);
			for (int j = 0; j < size; j++) {
				String data = "" + (Math.random() * 1234567) + "\n";
				goods.printLine(data);
				System.out.println(data);
				Thread.sleep(1000 + (int) (Math.random() * 734));
			}

			goods.save(name);
			ab.putGoods(name, goods);

		}
		ab.close();
	}
}
