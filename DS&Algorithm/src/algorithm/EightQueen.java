package algorithm;

public class EightQueen {

	private static int Count = 0;

	public static void main(String[] args) {
		int chess[][] = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chess[i][j] = 0;
			}
		}
		System.out.println("*********�˻ʺ�����*********");
		Queen(0, chess);
		System.out.println("*********����*********");
	}

	/**
	 * 
	 * @param row
	 *            ��ʼ��
	 * @param n
	 *            ����
	 * @param chess
	 *            ����
	 */
	public static void Queen(int row, int[][] chess) {

		// ��ʱ����
		int chess2[][] = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chess2[i][j] = chess[i][j];
			}
		}
		if (8 == row) {
			System.out.println("��" + (Count + 1) + "�ְڷ�:");
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					System.out.print(chess2[i][j] + " ");
				}
				System.out.println();
			}
			Count++;
		} else {
			// �ж����λ���Ƿ���Σ��
			// ���û��Σ�ռ���
			for (int j = 0; j < 8; j++) {
				if (NotDanger(row, j, chess)) {
					for (int i = 0; i < 8; i++) {
						chess2[row][i] = 0;
					}
					chess2[row][j] = 1;

					Queen(row + 1, chess2);
				}
			}

		}
	}

	/**
	 * �ж��Ƿ�Σ��
	 * 
	 * @param row
	 * @param j
	 * @param chess
	 * @return
	 */
	private static boolean NotDanger(int row, int j, int[][] chess) {

		for (int i = 0; i < 8; i++) {
			// �����з���
			if (chess[i][j] == 1 || chess[row][i] == 1) {
				return false;
			}
			// б�߷���
			// 1\ 3/
			// 4/ 2\
			if (((row - i >= 0 && j - i >= 0) && chess[row - i][j - i] == 1)
					|| ((row + i < 8 && j + i < 8) && chess[row + i][j + i] == 1)
					|| ((row - i >= 0 && j + i < 8) && chess[row - i][j + i] == 1)
					|| ((row + i < 8 && j - i >= 0) && chess[row + i][j - i] == 1)) {
				return false;
			}

		}
		return true;
	}
}
