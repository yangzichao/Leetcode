class Solution {
    public void rotate(int[][] matrix) {
        for( int i = 0; i < matrix.length/2.0; i ++ ){
            for ( int j = 0; j < matrix.length /2; j++){
                int c = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i] = matrix[matrix.length-1-i][matrix.length-1-j];
                matrix[matrix.length-1-i][matrix.length-1-j] = matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i] = c;
            }
        }
    }
}
