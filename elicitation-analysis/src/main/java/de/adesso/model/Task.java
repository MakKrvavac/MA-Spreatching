package de.adesso.model;


public class Task {
    public static final String[][] TASKS = {
            {"Change value", "Enter the value 100 into cell F9.", Group.P.toString()},
            {"Delete value", "Delete the contents of cell E8.", Group.P.toString()},
            {"Delete values", "Delete the contents of cells J7-J11.", Group.A.toString()},
            {"Add two values", "Calculate the sum of cells I8 and J8 in cell K8.", Group.A.toString(),},
            {"Insert column", "Insert a new column between columns B and C.", Group.A.toString()},
            {"Remove column", "Remove the whole column B from the table.", Group.A.toString()},
            {"Move cells", "Move cells A15-C15 to H3.", Group.A.toString()},
            {"Add multiple values", "Calculate the sum of cells E7-E11 in cell E13.", Group.B.toString(),},
            {"Format values", "Format the values in cells C7-C11 as Dollar amounts with two decimal places.", Group.B.toString(),},
            {"Frame cell", "Add a single frame around cells A15-C15.", Group.B.toString()},
            {"Transfer formatting", "Transfer the formatting of cell A13 to cells E13-J13.", Group.B.toString(),},
            {"Create pie chart", "Create a pie chart from the values in cells E8-J8.", Group.B.toString(),},
            {"Create bar chart", "Create a bar chart from the value in cells C7-C11.", Group.B.toString(),},
            {"Continue series", "Continue the series of values in row 6 (E6-J6) up to cell M6.", Group.B.toString(),},
            {"Transpose values", "Transpose the names in cells A7-A11 to row 17.", Group.B.toString(),},
            {"Sort data", "Sort the data in rows 7-11 alphabetically by the employee names in column A.", Group.C.toString(),},
            {"Calculate multiple sums", "For each month, calculate the sum of all employees' hours in cells E13-J13.", Group.C.toString(),},
            {"Construct formula", "For each employee, calculate the gross hourly rate in column D by factoring the overhead cost percentage from cell C15 into the net hourly rate in column C.", Group.C.toString(),},
            {"Conditional formatting", "Define a conditional formatting rule for cells C7-11 so values greater than 200 are displayed in red but others in green.", Group.C.toString(),},
            {"Multiply values", "Calculate the total salary of employee Smith in cell L7.", Group.C.toString(),}
    };
}
