# Baisa-Swarna-Hashira-Assignment

This project implements a simplified version of Shamir's Secret Sharing algorithm to find the constant term of a polynomial using Lagrange interpolation. Given a set of points encoded in a JSON format with different bases, this tool decodes the points, performs interpolation, and calculates the constant term.

## Problem Statement

The objective is to find the constant term \( c \) of a polynomial of degree \( m \), represented as:

\[
f(x) = a_m x^m + a_{m-1} x^{m-1} + \ldots + a_1 x + c
\]

Given a minimum number of points \((x, y)\) that lie on this polynomial, the program reconstructs \( f(x) \) using Lagrange interpolation to extract the constant term \( c \).

## Project Structure

- **`index.js`**: Contains the main logic to read input, decode points, perform Lagrange interpolation, and compute the constant term.
- **`testCase1.json`** and **`testCase2.json`**: Sample test cases provided in JSON format.
  
## Methodology

1. **Decoding**:
   Each \( y \) value in the JSON file is encoded in a specified base. The program decodes these values into base 10 using JavaScript’s `parseInt()` function.

2. **Lagrange Interpolation**:
   The interpolation is used to reconstruct the polynomial from the provided points and calculate the constant term.

3. **Output**:
   The final constant term \( c \) is printed for each test case.


## Usage

1. Place the JSON test files in the root directory as `testCase1.json` and `testCase2.json`.
2. Run the program using Node.js:

   ```bash
   node index.js
   ```

This will output the constant term \( c \) for both test cases.

## Example Input (JSON)

```json
{
    "keys": {
        "n": 4,
        "k": 3
    },
    "1": {
        "base": "10",
        "value": "4"
    },
    "2": {
        "base": "2",
        "value": "111"
    },
    "3": {
        "base": "10",
        "value": "12"
    },
    "6": {
        "base": "4",
        "value": "213"
    }
}
```

In this example:

- **n**: Total number of points given.
- **k**: Minimum number of points needed for interpolation.
- Each point is represented by an encoded `value` in a specific `base`.

## Functions

- **`decodeBase(value, base)`**: Converts encoded `y` values to base 10.
- **`lagrangeInterpolation(points)`**: Calculates the constant term \( c \) using Lagrange interpolation.
- **`findSecret(jsonInput)`**: Parses JSON input, decodes points, and computes the constant term.

## Lagrange Interpolation Explanation

For detailed information on Lagrange interpolation, refer to the `lagrangeInterpolation` function in `index.js`. This method constructs the polynomial from points, evaluating \( f(0) \) to find the constant term \( c \).

## Sample Output

```bash
Test Case 1 -> Secret: 30
Test Case 2 -> Secret: 20
```

## License

This project is open-source under the MIT License.


