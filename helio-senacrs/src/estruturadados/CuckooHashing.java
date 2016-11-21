package estruturadados;
// claase Hash cuco
public class CuckooHashing<K, V> {

	private static class TableElement<X, Y> {
		public final X key;
		public final Y value;
		public final int[] hashes;

		public TableElement(X key, Y value, int hashcount) {
			this.key = key;
			this.value = value;
			this.hashes = TableElement.getHashes(key, hashcount);
		}

		public static <X> int[] getHashes(X key, int hashcount) {
			int[] hashes = new int[hashcount];
			
			hashes[0] = key.hashCode() < 0 ? key.hashCode() * -1 : key.hashCode();
			
			for (int i = 1; i < hashcount; i++) {
				hashes[i] = String.format("%s%d", key.toString(), hashes[i - 1]).hashCode();
				if (hashes[i] < 0)
					hashes[i] *= -1;
			}
			return hashes;
		}
	}

	private int hashcount;

	private Object[] table = new Object[1000];

	private int numElements = 0;

	public CuckooHashing(int hashcount) {
		this.hashcount = hashcount;
	}

	public V get(K key) {
		int[] hashes = TableElement.getHashes(key, hashcount);
		for (int h : hashes) {
			int index = h % table.length;
			if (table[index] != null) {
				@SuppressWarnings("unchecked")
				TableElement<K, V> elem = (TableElement<K, V>) table[index];
				if (elem.key.equals(key)) {
					return elem.value;
				}
			}
		}
		throw new KeyNotFoundException();
	}

	public void put(K key, V value) {
		TableElement<K, V> elem = new TableElement<>(key, value, hashcount);
		cuckoo(elem, 0, 0);
	}

	private void cuckoo(TableElement<K, V> elem, int hash, int count) {
		if (count > 20) {
			rehash(elem);
			return;
		}

		int index = elem.hashes[hash] % table.length;
		if (table[index] == null) {
			table[index] = elem;
			numElements++;
		} else {
			@SuppressWarnings("unchecked")
			TableElement<K, V> old = (TableElement<K, V>) table[index];
			table[index] = elem;
			cuckoo(old, (hash + 1) % hashcount, ++count);
		}
	}

	private static boolean rehashing = false;

	private void rehash(TableElement<K, V> elem) {
		if (rehashing)
			throw new IllegalStateException();
		rehashing = true;
		doRehash(elem);
		rehashing = false;
	}

	@SuppressWarnings("unchecked")
	private void doRehash(TableElement<K, V> elem) {
		Object[] oldTable = table;
		table = new Object[table.length * 2];
		try {
			cuckoo(elem, 0, 0);
			for (Object o : oldTable) {
				if (o != null) {
					elem = (TableElement<K, V>) o;
					cuckoo(elem, 0, 0);
				}
			}
		} catch (IllegalStateException e) {
			table = oldTable;
		}
	}

}