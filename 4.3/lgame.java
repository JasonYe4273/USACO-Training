/*
ID: yuzhou.1
LANG: JAVA
TASK: lgame
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class lgame
{
	public static void main(String[] args) throws IOException
	{
		int[] POINTS = new int[]{ 2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7 };
		
		BufferedReader in = new BufferedReader(new FileReader("lgame.in"));
		BufferedReader dict = new BufferedReader(new FileReader("lgame.dict"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));
		
		NumLetter l = new NumLetter(in.readLine().trim().toCharArray(), POINTS);
		in.close();
		
		String word = dict.readLine();
		while (!word.equals("."))
		{
			l.addWord(word);
			word = dict.readLine();
		}
		dict.close();
		
		l.printMaxPoints(out);
		out.close();
	}
	
	static class NumLetter
	{
		ArrayList<Character> letters = new ArrayList<Character>();
		public int[] num;
		public int numUnique;
		public int maxCode;
		public ArrayList<String>[] codeToWord;
		public ArrayList<PointCombo> sortedPoints;
		
		@SuppressWarnings("unchecked")
		public NumLetter(char[] available, int[] POINTS)
		{
			num = new int[available.length];
			letters = new ArrayList<Character>();
			for (int i = 0; i < available.length; i++)
			{
				int idx = letters.indexOf(available[i]);
				if (idx == -1)
				{
					num[letters.size()]++;
					letters.add(available[i]);
				}
				else num[idx]++;
			}
			numUnique = letters.size();
			
			maxCode = encode(available);
			codeToWord = new ArrayList[maxCode + 1];
			for (int i = 0; i <= maxCode; i++) codeToWord[i] = new ArrayList<String>();
			
			sortedPoints = new ArrayList<PointCombo>();
			for (int c = 0; c <= maxCode; c++)
			{
				int[] numLetters = decode(c);
				int points = 0;
				for (int i = 0; i < numUnique; i++)
				{
					points += numLetters[i] * POINTS[letters.get(i) - 'a'];
				}
				sortedPoints.add(new PointCombo(c, points));
			}
			Collections.sort(sortedPoints);
		}
		
		@SuppressWarnings({ "unchecked" })
		public NumLetter(ArrayList<Character> letters, int[] numLetters)
		{
			num = numLetters;
			this.letters = letters;
			numUnique = letters.size();
			
			maxCode = encode(numLetters);
			codeToWord = new ArrayList[maxCode + 1];
			for (int i = 0; i <= maxCode; i++) codeToWord[i] = new ArrayList<String>();
		}
		
		public int encode(char[] word)
		{
			int[] numLetters = new int[numUnique];
			for (int i = 0; i < word.length; i++)
			{
				int idx = letters.indexOf(word[i]);
				if (idx == -1) return -1;
				numLetters[idx]++;
			}
			for (int i = 0; i < numUnique; i++) if (numLetters[i] > num[i]) return -1;
			
			int code = 0;
			for (int i = 0; i < numUnique; i++)
			{
				code *= (num[i] + 1);
				code += numLetters[i];
			}
			return code;
		}
		
		public int encode(int[] numLetters)
		{
			for (int i = 0; i < numUnique; i++) if (numLetters[i] > num[i]) return -1;
			
			int code = 0;
			for (int i = 0; i < numUnique; i++)
			{
				code *= (num[i] + 1);
				code += numLetters[i];
			}
			return code;
		}
		
		public int[] decode(int code)
		{
			int[] numLetters = new int[numUnique];
			for (int i = numUnique - 1; i >= 0; i--)
			{
				numLetters[i] += code % (num[i] + 1);
				code /= (num[i] + 1);
			}
			return numLetters;
		}
		
		public void addWord(String word)
		{
			int code = encode(word.toCharArray());
			if (code == -1) return;
			codeToWord[code].add(word);
		}
		
		public void sortLists()
		{
			for (int i = 0; i < maxCode; i++) Collections.sort(codeToWord[i]);
		}
		
		public boolean addAllPossible(int[] numLetters, ArrayList<FirstWord> firsts, ArrayList<String> firstsUsed)
		{
			boolean possible = false;
			
			int target = encode(numLetters);
			if (codeToWord[target].size() > 0)
			{
				possible = true;
				for (String w : codeToWord[target])
				{
					firsts.add(new FirstWord(w));
				}
			}
			
			NumLetter targetEncoder = new NumLetter(letters, numLetters);
			for (int i = 0; i < targetEncoder.maxCode; i++)
			{
				int idx = encode(targetEncoder.decode(i));
				int otherIdx = encode(targetEncoder.decode(targetEncoder.maxCode - i));
				if (codeToWord[idx].size() > 0 && codeToWord[otherIdx].size() > 0)
				{
					possible = true;
					for (String w : codeToWord[idx])
					{
						int startIdx = codeToWord[otherIdx].size();
						while (startIdx > 0 && w.compareTo(codeToWord[otherIdx].get(startIdx - 1)) < 0) startIdx--;
						if (startIdx < codeToWord[otherIdx].size())
						{
							if (!firstsUsed.contains(w))
							{
								firsts.add(new FirstWord(w, otherIdx, startIdx));
								firstsUsed.add(w);
							}
							else
							{
								FirstWord f = firsts.get(firstsUsed.indexOf(w));
								f.correspondingCode.add(otherIdx);
								f.idxToStart.add(startIdx);
							}
						}
					}
				}
			}
			return possible;
		}
		
		public void printMaxPoints(PrintWriter out)
		{
			sortLists();
			
			int maxPoints = -1;
			ArrayList<FirstWord> firsts = new ArrayList<FirstWord>();
			ArrayList<String> firstsUsed = new ArrayList<String>();
			for (int i = 0; i < sortedPoints.size(); i++)
			{
				if (maxPoints == -1 && addAllPossible(decode(sortedPoints.get(i).code), firsts, firstsUsed))
				{
					maxPoints = sortedPoints.get(i).points;
				}
				else if (maxPoints != -1 && sortedPoints.get(i).points != maxPoints) break;
				else addAllPossible(decode(sortedPoints.get(i).code), firsts, firstsUsed);
			}
			
			if (maxPoints == -1)
			{
				out.println(0);
				return;
			}
			
			out.println(maxPoints);
			Collections.sort(firsts);
			for (FirstWord f : firsts)
			{
				if (f.correspondingCode.size() == 0)
				{
					out.println(f.word);
					continue;
				}
				
				ArrayList<String> seconds = new ArrayList<String>();
				for (int i = 0; i < f.correspondingCode.size(); i++)
				{
					for (int j = f.idxToStart.get(i); j < codeToWord[f.correspondingCode.get(i)].size(); j++)
					{
						seconds.add(codeToWord[f.correspondingCode.get(i)].get(j));
					}
				}
				Collections.sort(seconds);
				
				for (String s : seconds)
				{
					out.println(f.word + " " + s);
				}
			}
		}
		
		public void print()
		{
			for (int i = 0; i < numUnique; i++) System.out.print(letters.get(i));
			System.out.println();
			for (int i = 0; i <= maxCode; i++)
			{
				if (codeToWord[i].size() > 0) System.out.println("Code " + i + ":");
				for (String w : codeToWord[i]) System.out.println(w);
			}
			
			for (PointCombo c : sortedPoints)
			{
				System.out.println(c.code + " gets " + c.points + " points.");
			}
		}
	}
	
	static class FirstWord implements Comparable<FirstWord>
	{
		public String word;
		public ArrayList<Integer> correspondingCode;
		public ArrayList<Integer> idxToStart;
		
		public FirstWord(String w)
		{
			word = w;
			correspondingCode = new ArrayList<Integer>();
			idxToStart = new ArrayList<Integer>();
		}
		
		public FirstWord(String w, int code, int startIdx)
		{
			word = w;
			correspondingCode = new ArrayList<Integer>();
			correspondingCode.add(code);
			idxToStart = new ArrayList<Integer>();
			idxToStart.add(startIdx);
		}
		
		@Override
		public int compareTo(FirstWord f)
		{
			return word.compareTo(f.word);
		}
	}
	
	static class PointCombo implements Comparable<PointCombo>
	{
		public int code;
		public int points;
		
		public PointCombo(int c, int p)
		{
			code = c;
			points = p;
		}
		
		@Override
		public int compareTo(PointCombo c)
		{
			if (points == c.points) return 0;
			return points > c.points ? -1 : 1;
		}
	}
}
